package kr.co.leteatgo.store.domain.store.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Town {

  @Column(name = "town_code", length = 30)
  private String code;
  @Column(name = "town_name", length = 30)
  private String name;

  public Town(String code, String name) {
    if (!StringUtils.hasText(code) || !StringUtils.hasText(name)) {
      throw new IllegalArgumentException("code and name must not be empty");
    }
    this.code = code;
    this.name = name;
  }
}
