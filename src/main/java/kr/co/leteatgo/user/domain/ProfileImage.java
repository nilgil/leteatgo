package kr.co.leteatgo.user.domain;

import java.net.URI;

import org.springframework.util.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ProfileImage {

	@Column(name = "profile_image")
	private String value;

	private ProfileImage(String value) {
		if (isBlank(value) || !validUrlFormat(value)) {
			throw new IllegalArgumentException("is invalid url format");
		}
		this.value = value;
	}

	public static ProfileImage valueOf(String value) {
		return new ProfileImage(value);
	}

	private static boolean isBlank(String value) {
		return !StringUtils.hasText(value);
	}

	private static boolean validUrlFormat(String value) {
		try {
			URI.create(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
