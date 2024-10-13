package kr.co.leteatgo.auth.domain.credential;

public interface Refreshable {

	boolean matchRefreshToken(RefreshToken refreshToken);

	void updateRefreshToken(RefreshToken refreshToken);
}
