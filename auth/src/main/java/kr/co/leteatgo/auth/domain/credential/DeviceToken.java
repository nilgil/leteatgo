package kr.co.leteatgo.auth.domain.credential;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class DeviceToken {

  @Column(name = "device_token", length = 500)
  private String value;

  private DeviceToken(String value) {
    if (!StringUtils.hasText(value)) {
      throw new IllegalArgumentException("is invalid device token");
    }
    this.value = value;
  }

  public static DeviceToken valueOf(String value) {
    return new DeviceToken(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceToken that = (DeviceToken) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "DeviceToken{" +
        "value='" + value + '\'' +
        '}';
  }
}
