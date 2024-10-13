package kr.co.leteatgo.notification.domain;

import org.springframework.util.StringUtils;

import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;

public record DeviceToken(String value) {

	public DeviceToken {
		if (!StringUtils.hasText(value)) {
			throw new LegException(ErrorCode.BAD_REQUEST, "cannot be empty device token");
		}
	}
}
