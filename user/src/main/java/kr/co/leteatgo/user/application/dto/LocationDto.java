package kr.co.leteatgo.user.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LocationDto(
    @NotBlank String alias,
    @NotNull Boolean marked,
    @NotNull AddressDto address,
    @NotNull CoordinateDto coordinate
) {

}
