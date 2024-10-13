package kr.co.leteatgo.auth.application.store.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginStoreAccountRequest(
	@NotBlank String loginId,
	@NotBlank String loginPwd
) {

}
