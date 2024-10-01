package kr.co.leteatgo.auth.domain.store;

import common.auth.jwt.AccountType;
import jakarta.persistence.Entity;
import java.util.List;
import java.util.UUID;
import kr.co.leteatgo.auth.domain.common.LegAccount;
import kr.co.leteatgo.auth.domain.credential.IdPwdCredential;
import kr.co.leteatgo.auth.domain.credential.LoginId;
import kr.co.leteatgo.auth.domain.credential.LoginPwd;
import kr.co.leteatgo.auth.domain.credential.RefreshToken;
import kr.co.leteatgo.auth.domain.credential.Refreshable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = LegAccount.class)
@Audited(withModifiedFlag = true)
@Entity
public class StoreAccount extends LegAccount implements Refreshable, IdPwdCredential {

  private LoginId loginId;
  private LoginPwd loginPwd;
  private RefreshToken refreshToken;

  @Builder
  private StoreAccount(UUID id, LoginId loginId, LoginPwd loginPwd) {
    super(id, List.of(AccountType.STORE.toString()), false);
    this.loginId = loginId;
    this.loginPwd = loginPwd;
  }

  @Override
  public AccountType accountType() {
    return AccountType.STORE;
  }

  @Override
  public boolean matchRefreshToken(RefreshToken refreshToken) {
    return this.refreshToken.equals(refreshToken);
  }

  @Override
  public void updateRefreshToken(RefreshToken refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Override
  public String loginId() {
    return this.loginId.getValue();
  }

  @Override
  public String encryptedPwd() {
    return this.loginPwd.getValue();
  }

  @Override
  public void updateLoginPwd(String encryptedPwd) {
    this.loginPwd = new LoginPwd(encryptedPwd);
  }
}
