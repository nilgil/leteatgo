package kr.co.leteatgo.auth.application.common;

import kr.co.leteatgo.common.auth.jwt.JwtSet;

public record JwtSetResponse(
	String accessToken,
	String refreshToken
) {

	public static JwtSetResponse of(JwtSet jwtSet) {
		return new JwtSetResponse(jwtSet.accessToken(), jwtSet.refreshToken());
	}
}
