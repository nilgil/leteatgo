package kr.co.leteatgo.auth.application.admin;

import common.auth.jwt.AccountType;
import common.auth.jwt.JwtParser;
import common.auth.jwt.JwtSet;
import common.auth.jwt.JwtToken;
import common.auth.jwt.TokenInfo;
import common.auth.jwt.TokenType;
import common.exception.ErrorCode;
import common.exception.LegException;
import java.util.UUID;
import kr.co.leteatgo.auth.application.admin.dto.LoginAdminAccountRequest;
import kr.co.leteatgo.auth.application.admin.dto.RegisterAdminAccountRequest;
import kr.co.leteatgo.auth.application.admin.dto.ReissueAdminAccountRequest;
import kr.co.leteatgo.auth.application.common.CredentialService;
import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import kr.co.leteatgo.auth.domain.admin.AdminAccount;
import kr.co.leteatgo.auth.domain.admin.AdminAccountRepository;
import kr.co.leteatgo.auth.domain.credential.RefreshToken;
import kr.co.leteatgo.auth.application.jwt.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminAccountServiceImpl implements AdminAccountService {

  private final AdminAccountMapper adminAccountMapper;
  private final AdminAccountRepository adminAccountRepository;
  private final CredentialService credentialService;
  private final JwtGenerator jwtGenerator;

  @Transactional
  @Override
  public void registerAdminAccount(RegisterAdminAccountRequest request) {
    AdminAccount adminAccount = adminAccountMapper.create(request);
    validDuplicatedLoginId(adminAccount.loginId());

    adminAccountRepository.save(adminAccount);
  }

  @Transactional
  @Override
  public void deleteAdminAccount(UUID adminId) {
    AdminAccount adminAccount = getAdminAccountById(adminId);
    adminAccountRepository.delete(adminAccount);
  }

  @Transactional
  @Override
  public JwtSetResponse loginAdminAccount(LoginAdminAccountRequest request) {
    AdminAccount adminAccount = getAdminAccountByLoginId(request.loginId());
    credentialService.validPassword(adminAccount, request.loginPwd());
    adminAccount.validAccount();

    JwtSet jwtSet = jwtGenerator.generateJwtSet(adminAccount);
    adminAccount.updateRefreshToken(RefreshToken.from(jwtSet));

    return JwtSetResponse.of(jwtSet);
  }

  @Transactional
  @Override
  public JwtSetResponse reissueAdminAccount(ReissueAdminAccountRequest request) {
    JwtToken jwtToken = JwtToken.valueOf(request.refreshToken());
    TokenInfo tokenInfo = JwtParser.parse(jwtToken);
    validAdminRefreshToken(tokenInfo);

    AdminAccount adminAccount = getAdminAccountById(tokenInfo.id());
    credentialService.validRefreshToken(adminAccount, RefreshToken.from(jwtToken));
    adminAccount.validAccount();

    JwtSet jwtSet = jwtGenerator.generateJwtSet(adminAccount);
    adminAccount.updateRefreshToken(RefreshToken.from(jwtSet));

    return JwtSetResponse.of(jwtSet);
  }

  private void validDuplicatedLoginId(String loginId) {
    adminAccountRepository.findByIdPasswordLoginId(loginId).ifPresent(adminAccount -> {
      throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated loginId");
    });
  }

  private AdminAccount getAdminAccountById(UUID adminId) {
    return adminAccountRepository.findById(adminId)
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
  }

  private AdminAccount getAdminAccountByLoginId(String loginId) {
    return adminAccountRepository.findByIdPasswordLoginId(loginId)
        .orElseThrow(() -> new LegException(ErrorCode.BAD_CREDENTIAL));
  }

  private static void validAdminRefreshToken(TokenInfo tokenInfo) {
    if (tokenInfo.accountType() != AccountType.ADMIN) {
      throw new LegException(ErrorCode.BAD_CREDENTIAL, "is not admin's token");
    }
    if (tokenInfo.tokenType() != TokenType.REFRESH) {
      throw new LegException(ErrorCode.BAD_CREDENTIAL, "is not refresh token");
    }
  }
}
