package kr.co.leteatgo.store.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.querydsl.core.annotations.QueryProjection;

public record LastStoreInfo(
	UUID storeId,
	LocalDateTime createdAt,
	String storeName,
	Integer distance,
	Long viewCount
) {

	@QueryProjection
	public LastStoreInfo {
	}
}
