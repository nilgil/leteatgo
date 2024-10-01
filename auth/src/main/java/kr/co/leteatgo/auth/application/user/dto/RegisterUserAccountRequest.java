package kr.co.leteatgo.auth.application.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RegisterUserAccountRequest(
    @NotNull UUID userId,
    @NotBlank String phoneNumber
) {

}
