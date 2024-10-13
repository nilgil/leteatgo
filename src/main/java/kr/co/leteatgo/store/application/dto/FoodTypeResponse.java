package kr.co.leteatgo.store.application.dto;

import kr.co.leteatgo.store.domain.foodtype.FoodType;

public record FoodTypeResponse(
	String foodType,
	Integer sort,
	String color,
	String icon
) {

	public FoodTypeResponse(FoodType foodType) {
		this(foodType.getName(),
			foodType.getSort(),
			foodType.getColor(),
			foodType.getIcon());
	}
}
