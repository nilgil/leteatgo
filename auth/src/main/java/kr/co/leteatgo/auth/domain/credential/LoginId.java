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
public class LoginId {

  @Column(name = "login_id", nullable = false, unique = true)
  private String value;

  public LoginId(String value) {
    if (!StringUtils.hasText(value) || value.length() > 20) {
      throw new LegException(ErrorCode.BAD_REQUEST, "로그인 아이디는 20자 이하로 입력해주세요.");
    }
    this.value = value;
  }
}
