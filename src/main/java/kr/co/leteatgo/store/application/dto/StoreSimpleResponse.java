package kr.co.leteatgo.store.application.dto;

import java.util.UUID;

import com.querydsl.core.annotations.QueryProjection;

public record StoreSimpleResponse(
	UUID storeId,
	String storeName,
	String mainImage,
	String town,
	Integer distance,
	Long viewCount,
	Integer minCookTime,
	String event
) {

	@QueryProjection
	public StoreSimpleResponse {
	}
}
