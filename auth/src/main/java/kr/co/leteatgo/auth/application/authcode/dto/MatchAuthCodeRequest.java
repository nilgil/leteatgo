package kr.co.leteatgo.auth.application.authcode.dto;

import jakarta.validation.constraints.NotBlank;

public record MatchAuthCodeRequest(
    @NotBlank String phoneNumber,
    @NotBlank String authCode
) {

}
