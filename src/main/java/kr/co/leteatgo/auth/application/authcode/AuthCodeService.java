package kr.co.leteatgo.auth.application.authcode;

public interface AuthCodeService {

	void sendAuthCode(String phoneNumber);

	Boolean matchAuthCode(String phoneNumber, String authCode);

	void deleteAuthCode(String phoneNumber);
}
