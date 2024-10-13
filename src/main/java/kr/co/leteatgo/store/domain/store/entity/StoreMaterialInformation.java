package kr.co.leteatgo.store.domain.store.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StoreMaterialInformation {

	private String originCountry;
	private String nutrition;
	private String allergy;
}
