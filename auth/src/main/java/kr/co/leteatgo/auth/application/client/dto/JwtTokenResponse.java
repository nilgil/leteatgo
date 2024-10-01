package kr.co.leteatgo.auth.application.client.dto;

import common.auth.jwt.JwtToken;

public record JwtTokenResponse(String accessToken) {

  public JwtTokenResponse(JwtToken jwtToken) {
    this(jwtToken.value());
  }
}
