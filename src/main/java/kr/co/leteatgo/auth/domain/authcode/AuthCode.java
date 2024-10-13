package kr.co.leteatgo.auth.domain.authcode;

import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class AuthCode {

	@Column(name = "auth_code", nullable = false, length = 10)
	private String value;

	public AuthCode() {
		this(RandomStringUtils.randomNumeric(6));
	}

	public AuthCode(String value) {
		if (!StringUtils.hasText(value) || value.length() != 6) {
			throw new IllegalArgumentException("auth code is blank or length != 6");
		}
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AuthCode that = (AuthCode)o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "AuthCode{" +
			"value='" + value + '\'' +
			'}';
	}
}
