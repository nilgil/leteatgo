package kr.co.leteatgo.auth.application.user;

import java.util.UUID;

import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import kr.co.leteatgo.auth.application.user.dto.LoginUserAccountRequest;
import kr.co.leteatgo.auth.application.user.dto.RegisterUserAccountRequest;
import kr.co.leteatgo.auth.application.user.dto.ReissueUserAccountRequest;

public interface UserAccountService {

	void validPhoneNumber(String phoneNumber);

	void registerUserAccount(RegisterUserAccountRequest request);

	void deleteUserAccount(UUID userId);

	JwtSetResponse loginUserAccount(LoginUserAccountRequest request);

	JwtSetResponse reissueUserAccount(ReissueUserAccountRequest request);
}
