package kr.co.leteatgo.store.application.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.util.UUID;

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
