package kr.co.leteatgo.store.domain.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import kr.co.leteatgo.store.domain.foodtype.FoodType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreFoodType extends BaseIdEntity {

	@ManyToOne
	private Store store;
	@ManyToOne
	private FoodType foodType;
}
