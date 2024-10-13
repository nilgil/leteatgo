package kr.co.leteatgo.store.application.dto;

import kr.co.leteatgo.store.domain.store.enums.ItemStatus;
import lombok.Builder;

@Builder
public record MenuOptionItemResponse(
	Long id,
	String name,
	Integer price,
	ItemStatus status,
	Integer sort
) {

}
