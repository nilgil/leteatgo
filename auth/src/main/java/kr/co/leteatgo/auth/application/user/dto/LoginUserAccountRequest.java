package kr.co.leteatgo.auth.application.user.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUserAccountRequest(
    @NotBlank String phoneNumber,
    @NotBlank String deviceToken,
    @NotBlank String authCode
) {

}
