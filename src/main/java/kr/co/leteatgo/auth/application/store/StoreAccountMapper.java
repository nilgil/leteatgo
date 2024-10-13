package kr.co.leteatgo.auth.application.store;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.co.leteatgo.auth.application.store.dto.RegisterStoreAccountRequest;
import kr.co.leteatgo.auth.domain.credential.LoginId;
import kr.co.leteatgo.auth.domain.credential.LoginPwd;
import kr.co.leteatgo.auth.domain.store.StoreAccount;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StoreAccountMapper {

	private final PasswordEncoder passwordEncoder;

	public StoreAccount create(RegisterStoreAccountRequest request) {
		String encodedPwd = passwordEncoder.encode(request.loginPwd());
		return StoreAccount.builder()
			.id(request.storeId())
			.loginId(new LoginId(request.loginId()))
			.loginPwd(new LoginPwd(encodedPwd))
			.build();
	}
}
