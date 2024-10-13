package kr.co.leteatgo.auth.domain.credential;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class IdPassword {

	@Column(nullable = false, unique = true)
	private String loginId;
	@Column(nullable = false)
	private String encryptedPwd;

	public IdPassword(String loginId, String encryptedPwd) {
		this.loginId = loginId;
		this.encryptedPwd = encryptedPwd;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		IdPassword that = (IdPassword)o;
		return Objects.equals(loginId, that.loginId) && Objects.equals(encryptedPwd, that.encryptedPwd);
	}

	@Override
	public int hashCode() {
		return Objects.hash(loginId, encryptedPwd);
	}
}
