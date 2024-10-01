package kr.co.leteatgo.store.application.dto;

import java.time.LocalTime;
import kr.co.leteatgo.store.domain.store.value.Week;
import lombok.Builder;

@Builder
public record BusinessHourDto(
    Week beginWeek,
    Week endWeek,
    LocalTime openAt,
    LocalTime closeAt,
    LocalTime breakStartAt,
    LocalTime breakEndAt
) {

}
