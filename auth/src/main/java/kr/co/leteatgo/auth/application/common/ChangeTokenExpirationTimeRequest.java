package kr.co.leteatgo.auth.application.common;


import common.auth.jwt.TokenType;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;

public record ChangeTokenExpirationTimeRequest(
    @NotNull TokenType tokenType,
    @NotNull Duration expirationTime
) {

}
