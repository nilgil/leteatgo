package kr.co.leteatgo.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

  @Column(name = "email", length = 50, unique = true)
  private String value;

  public Email(String value) {
    if (isBlank(value) || !validEmailFormat(value)) {
      throw new IllegalArgumentException("is invalid email format");
    }
    this.value = value;
  }

  private static boolean isBlank(String value) {
    return !StringUtils.hasText(value);
  }

  private static boolean validEmailFormat(String value) {
    return value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
  }

}
