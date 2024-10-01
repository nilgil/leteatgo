package kr.co.leteatgo.store.application.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.UUID;

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
