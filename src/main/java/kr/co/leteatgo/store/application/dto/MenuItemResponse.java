package kr.co.leteatgo.store.application.dto;

import java.util.List;

import kr.co.leteatgo.store.domain.store.enums.ItemStatus;
import lombok.Builder;

@Builder
public record MenuItemResponse(
	Long id,
	String name,
	String description,
	String image,
	Integer price,
	Integer sort,
	ItemStatus status,
	List<MenuOptionGroupResponse> optionGroups
) {

}
