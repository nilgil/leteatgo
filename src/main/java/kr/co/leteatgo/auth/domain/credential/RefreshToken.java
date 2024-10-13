package kr.co.leteatgo.auth.domain.credential;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.co.leteatgo.common.auth.jwt.JwtSet;
import kr.co.leteatgo.common.auth.jwt.JwtToken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class RefreshToken {

	@Column(name = "refresh_token", length = 1000)
	private String value;

	private RefreshToken(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("is blank value");
		}
		this.value = value;
	}

	public static RefreshToken valueOf(String value) {
		return new RefreshToken(value);
	}

	public static RefreshToken from(JwtSet jwtSet) {
		return new RefreshToken(jwtSet.refreshToken());
	}

	public static RefreshToken from(JwtToken jwtToken) {
		return new RefreshToken(jwtToken.value());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RefreshToken that = (RefreshToken)o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
