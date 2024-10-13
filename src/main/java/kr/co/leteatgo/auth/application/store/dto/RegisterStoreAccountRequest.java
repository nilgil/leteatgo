package kr.co.leteatgo.auth.application.store.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterStoreAccountRequest(
	@NotNull UUID storeId,
	@NotBlank String loginId,
	@NotBlank String loginPwd
) {

}
