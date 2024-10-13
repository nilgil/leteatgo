package kr.co.leteatgo.store.application.dto;

import java.util.List;

import kr.co.leteatgo.store.domain.store.entity.MenuItemOptionGroup;
import kr.co.leteatgo.store.domain.store.enums.ItemStatus;

public record AddMenuItemRequest(
	String name,
	String description,
	Boolean hasImage,
	Integer price,
	Integer sort,
	ItemStatus status,
	List<MenuItemOptionGroup> itemOptionGroups
) {

}
