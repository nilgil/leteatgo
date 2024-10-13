package kr.co.leteatgo.store.domain.store.value;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PositiveOrZero {

	private Integer value;

	public PositiveOrZero(Integer value) {
		if (value < 0) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	public static PositiveOrZero valueOf(Integer value) {
		return new PositiveOrZero(value);
	}
}
