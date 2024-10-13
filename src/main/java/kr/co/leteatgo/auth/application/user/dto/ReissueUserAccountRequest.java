package kr.co.leteatgo.auth.application.user.dto;

import jakarta.validation.constraints.NotBlank;

public record ReissueUserAccountRequest(
	@NotBlank String deviceToken,
	@NotBlank String refreshToken
) {

}
