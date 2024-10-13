package kr.co.leteatgo.auth.application.common;

import java.time.Duration;

import jakarta.validation.constraints.NotNull;
import kr.co.leteatgo.common.auth.jwt.TokenType;

public record ChangeTokenExpirationTimeRequest(
	@NotNull TokenType tokenType,
	@NotNull Duration expirationTime
) {

}
