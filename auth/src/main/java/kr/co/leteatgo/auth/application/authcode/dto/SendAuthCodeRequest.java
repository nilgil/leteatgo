package kr.co.leteatgo.auth.application.authcode.dto;

import jakarta.validation.constraints.NotBlank;

public record SendAuthCodeRequest(@NotBlank String phoneNumber) {

}
