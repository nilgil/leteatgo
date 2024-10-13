package kr.co.leteatgo.store.domain.store.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StoreDescription {

	@Column(name = "description", length = 500)
	private String value;

	private StoreDescription(String value) {
		this.value = value;
	}

	public static StoreDescription valueOf(String value) {
		return new StoreDescription(value);
	}
}
