package kr.co.leteatgo.store.application.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record MenuOptionGroupResponse(
	Long id,
	String name,
	ChoiceLimitDto limit,
	Integer sort,
	List<MenuOptionItemResponse> optionItems
) {

}
