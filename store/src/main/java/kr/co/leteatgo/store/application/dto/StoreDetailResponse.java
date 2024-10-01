package kr.co.leteatgo.store.application.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record StoreDetailResponse(
    UUID storeId,
    String storeName,
    String phoneNumber,
    String address,
    String dayOff,
    String event,
    CoordinateDto coordinate,
    List<BusinessHourDto> businessHours,
    List<String> images,
    Long viewCount
) {

}