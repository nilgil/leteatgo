package kr.co.leteatgo.auth.application.common;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.co.leteatgo.auth.domain.credential.ApiKeyCredential;
import kr.co.leteatgo.auth.domain.credential.DeviceToken;
import kr.co.leteatgo.auth.domain.credential.IdPwdCredential;
import kr.co.leteatgo.auth.domain.credential.PhoneCredential;
import kr.co.leteatgo.auth.domain.credential.RefreshToken;
import kr.co.leteatgo.auth.domain.credential.Refreshable;
import kr.co.leteatgo.common.auth.ApiKey;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CredentialService {

	private final PasswordEncoder passwordEncoder;

	public void validRefreshToken(Refreshable account, RefreshToken refreshToken) {
		if (!account.matchRefreshToken(refreshToken)) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL, "invalid refresh token");
		}
	}

	public void validPassword(IdPwdCredential account, String rawPwd) {
		if (!passwordEncoder.matches(rawPwd, account.encryptedPwd())) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL, "invalid password");
		}
	}

	public void validDeviceToken(PhoneCredential account, DeviceToken deviceToken) {
		if (!account.matchDeviceToken(deviceToken)) {
			throw new LegException(ErrorCode.NO_REGISTERED_DEVICE);
		}
	}

	public void validApiKey(ApiKeyCredential account, ApiKey apiKey) {
		if (!account.matchApiKey(apiKey.value())) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL, "invalid api key");
		}
	}
}
