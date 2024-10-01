package kr.co.leteatgo.store.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record LocationDto(
    @Valid @NotNull AddressDto address,
    @Valid @NotNull CoordinateDto coordinate
) {

}
