package kr.co.leteatgo.user.application.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangeEmailRequest(@NotBlank String email) {

}
