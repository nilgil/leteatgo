package kr.co.leteatgo.auth.domain.credential;

import common.exception.ErrorCode;
import common.exception.LegException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class LoginPwd {

  @Column(name = "login_pwd", nullable = false)
  private String value;

  public LoginPwd(String encryptedPwd) {
    if (!StringUtils.hasText(encryptedPwd) || encryptedPwd.length() < 20) {
      throw new LegException(ErrorCode.BAD_REQUEST);
    }
    this.value = encryptedPwd;
  }

  public boolean match(LoginPwd loginPwd) {
    return value.equals(loginPwd.value);
  }
}
