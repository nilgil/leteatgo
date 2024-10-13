package kr.co.leteatgo.store.domain.store.entity;

import org.springframework.util.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class LicenseNumber {

	@Column(name = "license_number", length = 30, nullable = false)
	private String value;

	public LicenseNumber(String value) {
		if (!StringUtils.hasText(value)) {
			throw new LegException(ErrorCode.BAD_REQUEST, "licenseNumber must not be blank");
		}
		if (!validLicenseNumber(value)) {
			throw new LegException(ErrorCode.BAD_REQUEST,
				"invalid format of licenseNumber, ex. 123-45-67890");
		}
		this.value = value;
	}

	private static boolean validLicenseNumber(String value) {
		return value.matches("([0-9]{3})-([0-9]{2})-([0-9]{5})");
	}
}
