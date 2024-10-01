package kr.co.leteatgo.store.application.dto;

import jakarta.validation.constraints.NotNull;

public record CoordinateDto(
    @NotNull Double lng,
    @NotNull Double lat
) {

}
