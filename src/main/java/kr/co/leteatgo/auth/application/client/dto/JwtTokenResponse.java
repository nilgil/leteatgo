package kr.co.leteatgo.auth.application.client.dto;

import kr.co.leteatgo.common.auth.jwt.JwtToken;

public record JwtTokenResponse(String accessToken) {

	public JwtTokenResponse(JwtToken jwtToken) {
		this(jwtToken.value());
	}
}
