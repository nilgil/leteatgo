package kr.co.leteatgo.store.application.dto;

import kr.co.leteatgo.store.domain.store.enums.StoreStatus;

public record ChangeStoreStatusRequest(StoreStatus status) {

}
