package kr.co.leteatgo.store.domain.store.entity;

import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StoreFoodTypes {

	@OneToMany(mappedBy = "store")
	private List<StoreFoodType> foodTypes;
}
