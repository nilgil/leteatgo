package kr.co.leteatgo.auth.domain.credential;

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
public class LoginPwd {

	@Column(name = "login_pwd", nullable = false)
	private String value;

	public LoginPwd(String encryptedPwd) {
		if (!StringUtils.hasText(encryptedPwd) || encryptedPwd.length() < 20) {
			throw new LegException(ErrorCode.BAD_REQUEST);
		}
		this.value = encryptedPwd;
	}

	public boolean match(LoginPwd loginPwd) {
		return value.equals(loginPwd.value);
	}
}
