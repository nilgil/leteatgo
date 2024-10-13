package kr.co.leteatgo.user.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest(
	@NotBlank String phoneNumber,
	@NotBlank String nickname,
	@NotNull RegisterLocationDto location,
	@NotNull TermAgreementsDto termAgreements
) {

	public record RegisterLocationDto(
		@NotNull AddressDto address,
		@NotNull CoordinateDto coordinate
	) {

	}

}

