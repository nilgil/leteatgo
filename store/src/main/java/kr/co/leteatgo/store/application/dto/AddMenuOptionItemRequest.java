package kr.co.leteatgo.store.application.dto;

import kr.co.leteatgo.store.domain.store.enums.ItemStatus;

public record AddMenuOptionItemRequest(
    String name,
    Integer price,
    ItemStatus status,
    Integer sort
) {

}
