package kr.co.leteatgo.store.application.dto;

import java.util.UUID;
import kr.co.leteatgo.store.domain.store.enums.StoreSearchSort;
import lombok.Builder;

@Builder
public record SearchStoresCriteria(
    UUID prevId,
    Integer size,
    StoreSearchSort sort,
    String foodType,
    String search,
    Integer maxDistanceMeter,
    CoordinateDto coordinate
) {

}
