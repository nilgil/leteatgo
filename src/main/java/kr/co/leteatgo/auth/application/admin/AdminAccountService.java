package kr.co.leteatgo.auth.application.admin;

import java.util.UUID;

import kr.co.leteatgo.auth.application.admin.dto.LoginAdminAccountRequest;
import kr.co.leteatgo.auth.application.admin.dto.RegisterAdminAccountRequest;
import kr.co.leteatgo.auth.application.admin.dto.ReissueAdminAccountRequest;
import kr.co.leteatgo.auth.application.common.JwtSetResponse;

public interface AdminAccountService {

	void registerAdminAccount(RegisterAdminAccountRequest request);

	void deleteAdminAccount(UUID adminId);

	JwtSetResponse loginAdminAccount(LoginAdminAccountRequest request);

	JwtSetResponse reissueAdminAccount(ReissueAdminAccountRequest request);
}
