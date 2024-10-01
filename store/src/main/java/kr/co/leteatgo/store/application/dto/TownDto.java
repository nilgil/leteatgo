package kr.co.leteatgo.store.application.dto;

import jakarta.validation.constraints.NotNull;

public record TownDto(
    @NotNull String code,
    @NotNull String name
) {

}
