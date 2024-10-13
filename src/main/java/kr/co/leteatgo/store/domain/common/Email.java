package kr.co.leteatgo.store.domain.common;

import org.springframework.util.StringUtils;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

	private String value;

	public Email(String value) {
		if (!StringUtils.hasText(value) || !validFormat(value)) {
			throw new IllegalArgumentException("invalid value");
		}
		this.value = value;
	}

	private static boolean validFormat(String value) {
		return value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}
}
