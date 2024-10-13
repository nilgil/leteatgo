package kr.co.leteatgo.store.domain.store.value;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Money {

	private Integer value;

	public Money(Integer value) {
		this.value = value;
	}

	public static Money valueOf(Integer value) {
		return new Money(value);
	}
}
