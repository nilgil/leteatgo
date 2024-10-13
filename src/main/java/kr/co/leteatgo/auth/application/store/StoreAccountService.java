package kr.co.leteatgo.auth.application.store;

import java.util.UUID;

import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import kr.co.leteatgo.auth.application.store.dto.LoginStoreAccountRequest;
import kr.co.leteatgo.auth.application.store.dto.RegisterStoreAccountRequest;
import kr.co.leteatgo.auth.application.store.dto.ReissueStoreAccountRequest;

public interface StoreAccountService {

	void validLoginId(String loginId);

	void registerStoreAccount(RegisterStoreAccountRequest request);

	void deleteStoreAccount(UUID storeId);

	JwtSetResponse loginStoreAccount(LoginStoreAccountRequest request);

	JwtSetResponse reissueStoreAccount(ReissueStoreAccountRequest request);

	void activeStoreAccount(UUID storeId);
}
