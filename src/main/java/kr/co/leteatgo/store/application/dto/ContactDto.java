package kr.co.leteatgo.store.application.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record ContactDto(
	@NotBlank String phoneNumber,
	@Nullable String email
) {

}
