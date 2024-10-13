package kr.co.leteatgo.store.domain.common;

import org.springframework.util.StringUtils;

import jakarta.persistence.Embeddable;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PhoneNumber {

	private String value;

	public PhoneNumber(String value) {
		if (!StringUtils.hasText(value)) {
			throw new LegException(ErrorCode.BAD_REQUEST, "phoneNumber must not be blank");
		}
		if (!validPhoneNumber(value)) {
			throw new LegException(ErrorCode.BAD_REQUEST,
				"invalid format of phoneNumber, ex. 010-1234-5678");
		}
		this.value = value;
	}

	private static boolean validPhoneNumber(String value) {
		return value.matches("^(01[016789]|02|0[3-9][0-9]|0507)-[0-9]{3,4}-[0-9]{4}$");
	}
}
