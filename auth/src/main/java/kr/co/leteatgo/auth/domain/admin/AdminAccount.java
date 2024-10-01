package kr.co.leteatgo.auth.domain.admin;

import common.auth.jwt.AccountType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import java.util.List;
import kr.co.leteatgo.auth.domain.common.LegAccount;
import kr.co.leteatgo.auth.domain.credential.IdPassword;
import kr.co.leteatgo.auth.domain.credential.IdPwdCredential;
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
public class AdminAccount extends LegAccount implements Refreshable, IdPwdCredential {

  private String name;
  @Embedded
  private IdPassword idPassword;
  @Embedded
  private RefreshToken refreshToken;

  @Builder
  private AdminAccount(String name, String loginId, String loginPwd) {
    super(List.of(AccountType.ADMIN.toString()));
    this.name = name;
    this.idPassword = new IdPassword(loginId, loginPwd);
  }

  @Override
  public AccountType accountType() {
    return AccountType.ADMIN;
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
    return idPassword.getLoginId();
  }

  @Override
  public String encryptedPwd() {
    return idPassword.getEncryptedPwd();
  }

  @Override
  public void updateLoginPwd(String encryptedPwd) {
    this.idPassword = new IdPassword(idPassword.getLoginId(), encryptedPwd);
  }
}
