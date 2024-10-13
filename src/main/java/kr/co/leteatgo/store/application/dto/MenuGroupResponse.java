package kr.co.leteatgo.store.application.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record MenuGroupResponse(
	Long id,
	String name,
	Integer sort,
	List<MenuItemResponse> items
) {

}
