package kr.co.leteatgo.store.domain.store.value;

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
public class StoreName {

	@Column(name = "name", length = 30, nullable = false, unique = true)
	private String value;

	public StoreName(String value) {
		if (!StringUtils.hasText(value)) {
			throw new LegException(ErrorCode.BAD_REQUEST, "store name must not be empty");
		}
		if (value.length() > 30) {
			throw new LegException(ErrorCode.BAD_REQUEST, "store name length is over 30");
		}
		this.value = value;
	}
}
