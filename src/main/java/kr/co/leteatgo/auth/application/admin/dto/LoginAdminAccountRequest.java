package kr.co.leteatgo.auth.application.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginAdminAccountRequest(
	@NotBlank String loginId,
	@NotBlank String loginPwd
) {

}
