package kr.co.leteatgo.user.domain;

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
public class Nickname {

	@Column(name = "nickname", length = 50, unique = true, nullable = false)
	private String value;

	public Nickname(String value) {
		if (isBlank(value) || invalidNickname(value)) {
			throw new LegException(ErrorCode.INVALID_FORMAT, "is invalid nickname");
		}
		this.value = value;
	}

	private static boolean isBlank(String nickname) {
		return !StringUtils.hasText(nickname);
	}

	private static boolean invalidNickname(String nickname) {
		return !nickname.matches("^[0-9a-zA-Z가-힣]{2,10}$");
	}
}
