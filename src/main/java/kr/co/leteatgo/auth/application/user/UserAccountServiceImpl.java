package kr.co.leteatgo.auth.application.user;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.auth.application.authcode.AuthCodeService;
import kr.co.leteatgo.auth.application.common.CredentialService;
import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import kr.co.leteatgo.auth.application.jwt.JwtGenerator;
import kr.co.leteatgo.auth.application.user.dto.LoginUserAccountRequest;
import kr.co.leteatgo.auth.application.user.dto.RegisterUserAccountRequest;
import kr.co.leteatgo.auth.application.user.dto.ReissueUserAccountRequest;
import kr.co.leteatgo.auth.domain.authcode.AuthCode;
import kr.co.leteatgo.auth.domain.credential.DeviceToken;
import kr.co.leteatgo.auth.domain.credential.PhoneNumber;
import kr.co.leteatgo.auth.domain.credential.RefreshToken;
import kr.co.leteatgo.auth.domain.user.UserAccount;
import kr.co.leteatgo.auth.domain.user.UserAccountRepository;
import kr.co.leteatgo.common.auth.jwt.AccountType;
import kr.co.leteatgo.common.auth.jwt.JwtParser;
import kr.co.leteatgo.common.auth.jwt.JwtSet;
import kr.co.leteatgo.common.auth.jwt.JwtToken;
import kr.co.leteatgo.common.auth.jwt.TokenInfo;
import kr.co.leteatgo.common.auth.jwt.TokenType;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	private final UserAccountMapper userAccountMapper;
	private final UserAccountRepository userAccountRepository;
	private final CredentialService credentialService;
	private final AuthCodeService authCodeService;
	private final JwtGenerator jwtGenerator;

	private static void validUserRefreshToken(TokenInfo tokenInfo) {
		if (tokenInfo.accountType() != AccountType.USER) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL, "is not user's token");
		}
		if (tokenInfo.tokenType() != TokenType.REFRESH) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL, "is not refresh token");
		}
	}

	@Transactional(readOnly = true)
	@Override
	public void validPhoneNumber(String value) {
		PhoneNumber phoneNumber = new PhoneNumber(value);

		userAccountRepository.findByPhoneNumber(phoneNumber).ifPresent(userAccount -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated phoneNumber");
		});
	}

	@Transactional
	@Override
	public void registerUserAccount(RegisterUserAccountRequest request) {
		UserAccount userAccount = userAccountMapper.create(request);
		validDuplicatedId(userAccount.id());
		validDuplicatedPhoneNumber(userAccount.phoneNumber());

		userAccountRepository.save(userAccount);
	}

	@Transactional
	@Override
	public void deleteUserAccount(UUID userId) {
		UserAccount userAccount = getUserAccountById(userId);
		userAccountRepository.delete(userAccount);
	}

	@Transactional
	@Override
	public JwtSetResponse loginUserAccount(LoginUserAccountRequest request) {
		PhoneNumber phoneNumber = new PhoneNumber(request.phoneNumber());
		DeviceToken deviceToken = DeviceToken.valueOf(request.deviceToken());
		AuthCode authCode = new AuthCode(request.authCode());

		validAuthCode(phoneNumber, authCode);
		UserAccount userAccount = getUserByPhoneNumber(phoneNumber);
		userAccount.validAccount();

		JwtSet jwtSet = jwtGenerator.generateJwtSet(userAccount);
		userAccount.updateRefreshToken(RefreshToken.from(jwtSet));
		userAccount.updateDeviceToken(deviceToken);

		deleteAuthCode(phoneNumber);
		return JwtSetResponse.of(jwtSet);
	}

	@Transactional
	@Override
	public JwtSetResponse reissueUserAccount(ReissueUserAccountRequest request) {
		JwtToken jwtToken = JwtToken.valueOf(request.refreshToken());
		TokenInfo tokenInfo = JwtParser.parse(jwtToken);
		validUserRefreshToken(tokenInfo);

		UserAccount userAccount = getUserAccountById(tokenInfo.id());
		credentialService.validRefreshToken(userAccount, RefreshToken.from(jwtToken));
		credentialService.validDeviceToken(userAccount, DeviceToken.valueOf(request.deviceToken()));
		userAccount.validAccount();

		JwtSet jwtSet = jwtGenerator.generateJwtSet(userAccount);
		userAccount.updateRefreshToken(RefreshToken.from(jwtSet));

		return JwtSetResponse.of(jwtSet);
	}

	private void validDuplicatedId(UUID userId) {
		userAccountRepository.findById(userId).ifPresent(userAccount -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated id");
		});
	}

	private void validDuplicatedPhoneNumber(PhoneNumber phoneNumber) {
		userAccountRepository.findByPhoneNumber(phoneNumber).ifPresent(userAccount -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated phoneNumber");
		});
	}

	private void validAuthCode(PhoneNumber phoneNumber, AuthCode authCode) {
		if (!authCodeService.matchAuthCode(phoneNumber.getValue(), authCode.getValue())) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL, "not match auth code");
		}
	}

	private void deleteAuthCode(PhoneNumber phoneNumber) {
		authCodeService.deleteAuthCode(phoneNumber.getValue());
	}

	private UserAccount getUserByPhoneNumber(PhoneNumber phoneNumber) {
		return userAccountRepository.findByPhoneNumber(phoneNumber).orElseThrow(
			() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE,
				"cannot find user by phoneNumber number"));
	}

	private UserAccount getUserAccountById(UUID uuid) {
		return userAccountRepository.findById(uuid).orElseThrow(
			() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE, "cannot find user by id"));
	}
}
