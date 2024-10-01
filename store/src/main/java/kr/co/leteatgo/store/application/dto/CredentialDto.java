package kr.co.leteatgo.store.application.dto;

import jakarta.validation.constraints.NotBlank;

public record CredentialDto(
    @NotBlank String loginId,
    @NotBlank String loginPwd
) {

}
