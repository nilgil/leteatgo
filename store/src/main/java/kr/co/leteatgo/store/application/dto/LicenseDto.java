package kr.co.leteatgo.store.application.dto;

import jakarta.validation.constraints.NotBlank;

public record LicenseDto(
    @NotBlank String licenseNumber,
    @NotBlank String businessType,
    @NotBlank String industry
) {

}
