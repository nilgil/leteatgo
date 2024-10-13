package kr.co.leteatgo.auth.application.store;

import java.time.Duration;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.auth.application.common.CredentialService;
import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import kr.co.leteatgo.auth.application.jwt.JwtGenerator;
import kr.co.leteatgo.auth.application.store.dto.LoginStoreAccountRequest;
import kr.co.leteatgo.auth.application.store.dto.RegisterStoreAccountRequest;
import kr.co.leteatgo.auth.application.store.dto.ReissueStoreAccountRequest;
import kr.co.leteatgo.auth.domain.common.LegAccount;
import kr.co.leteatgo.auth.domain.credential.LoginId;
import kr.co.leteatgo.auth.domain.credential.RefreshToken;
import kr.co.leteatgo.auth.domain.store.StoreAccount;
import kr.co.leteatgo.auth.domain.store.StoreAccountRepository;
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
public class StoreAccountServiceImpl implements StoreAccountService {

	private static final Duration STORE_ACCESS_TOKEN_EXP_TIME = Duration.ofHours(1);
	private static final Duration STORE_REFRESH_TOKEN_EXP_TIME = Duration.ofDays(2);

	private final StoreAccountMapper storeAccountMapper;
	private final StoreAccountRepository storeAccountRepository;
	private final CredentialService credentialService;
	private final JwtGenerator jwtGenerator;

	private static void validStoreAccountRefreshToken(TokenInfo tokenInfo) {
		if (tokenInfo.tokenType() != TokenType.REFRESH) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL, "is not refresh token");
		}
		if (tokenInfo.accountType() != AccountType.STORE) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL, "is not store's token");
		}
	}

	@Transactional(readOnly = true)
	@Override
	public void validLoginId(String value) {
		LoginId loginId = new LoginId(value);

		storeAccountRepository.findByLoginId(loginId).ifPresent(storeAccount -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated login id");
		});
	}

	@Transactional
	@Override
	public void registerStoreAccount(RegisterStoreAccountRequest request) {
		StoreAccount storeAccount = storeAccountMapper.create(request);
		validDuplicatedId(storeAccount.id());
		validDuplicatedLoginId(storeAccount.loginId());

		storeAccountRepository.save(storeAccount);
	}

	@Transactional
	@Override
	public void deleteStoreAccount(UUID storeId) {
		StoreAccount storeAccount = getStoreAccountById(storeId);
		storeAccountRepository.delete(storeAccount);
	}

	@Transactional
	@Override
	public JwtSetResponse loginStoreAccount(LoginStoreAccountRequest request) {
		StoreAccount storeAccount = getStoreAccountByLoginId(request.loginId());
		credentialService.validPassword(storeAccount, request.loginPwd());
		storeAccount.validAccount();

		JwtSet jwtSet = generateJwtSet(storeAccount);
		storeAccount.updateRefreshToken(RefreshToken.from(jwtSet));

		return JwtSetResponse.of(jwtSet);
	}

	@Transactional
	@Override
	public JwtSetResponse reissueStoreAccount(ReissueStoreAccountRequest request) {
		JwtToken jwtToken = JwtToken.valueOf(request.refreshToken());
		TokenInfo tokenInfo = JwtParser.parse(jwtToken);
		validStoreAccountRefreshToken(tokenInfo);

		StoreAccount storeAccount = getStoreAccountById(tokenInfo.id());
		credentialService.validRefreshToken(storeAccount, RefreshToken.from(jwtToken));
		storeAccount.validAccount();

		JwtSet jwtSet = generateJwtSet(storeAccount);
		storeAccount.updateRefreshToken(RefreshToken.from(jwtSet));

		return JwtSetResponse.of(jwtSet);
	}

	@Transactional
	@Override
	public void activeStoreAccount(UUID storeId) {
		StoreAccount storeAccount = getStoreAccountById(storeId);
		storeAccount.active();
	}

	private StoreAccount getStoreAccountById(UUID storeId) {
		return storeAccountRepository.findById(storeId).orElseThrow(
			() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
	}

	private StoreAccount getStoreAccountByLoginId(String loginId) {
		return storeAccountRepository.findByLoginId(new LoginId(loginId))
			.orElseThrow(() -> new LegException(ErrorCode.BAD_CREDENTIAL));
	}

	private void validDuplicatedId(UUID userId) {
		storeAccountRepository.findById(userId).ifPresent(storeAccount -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated id");
		});
	}

	private void validDuplicatedLoginId(String loginId) {
		storeAccountRepository.findByLoginId(new LoginId(loginId)).ifPresent(storeAccount -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated login id");
		});
	}

	private JwtSet generateJwtSet(LegAccount account) {
		return jwtGenerator.generateJwtSet(account,
			STORE_ACCESS_TOKEN_EXP_TIME, STORE_REFRESH_TOKEN_EXP_TIME);
	}
}
