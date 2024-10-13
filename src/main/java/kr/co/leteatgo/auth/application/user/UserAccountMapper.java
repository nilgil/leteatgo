package kr.co.leteatgo.auth.application.user;

import org.springframework.stereotype.Component;

import kr.co.leteatgo.auth.application.user.dto.RegisterUserAccountRequest;
import kr.co.leteatgo.auth.domain.credential.PhoneNumber;
import kr.co.leteatgo.auth.domain.user.UserAccount;

@Component
public class UserAccountMapper {

	public UserAccount create(RegisterUserAccountRequest request) {
		return UserAccount.builder()
			.id(request.userId())
			.phoneNumber(new PhoneNumber(request.phoneNumber()))
			.build();
	}
}
