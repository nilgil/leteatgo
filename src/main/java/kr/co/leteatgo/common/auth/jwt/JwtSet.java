package kr.co.leteatgo.common.auth.jwt;

public class JwtSet {

	private final JwtToken accessToken;
	private final JwtToken refreshToken;

	public JwtSet(JwtToken accessToken, JwtToken refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String accessToken() {
		return this.accessToken.value();
	}

	public String refreshToken() {
		return this.refreshToken.value();
	}
}
