package kr.co.leteatgo.notification.domain;


import common.exception.ErrorCode;
import common.exception.LegException;
import org.springframework.util.StringUtils;

public record DeviceToken(String value) {

  public DeviceToken {
    if (!StringUtils.hasText(value)) {
      throw new LegException(ErrorCode.BAD_REQUEST, "cannot be empty device token");
    }
  }
}
