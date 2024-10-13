package kr.co.leteatgo.auth.application.user.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserAccountRequest(
	@NotNull UUID userId,
	@NotBlank String phoneNumber
) {

}
