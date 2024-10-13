package kr.co.leteatgo.auth.application.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterAdminAccountRequest(
	@NotBlank String name,
	@NotBlank String loginId,
	@NotBlank String loginPwd
) {

}
