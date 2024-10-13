package kr.co.leteatgo.auth.presentation;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import kr.co.leteatgo.auth.application.store.StoreAccountService;
import kr.co.leteatgo.auth.application.store.dto.LoginStoreAccountRequest;
import kr.co.leteatgo.auth.application.store.dto.RegisterStoreAccountRequest;
import kr.co.leteatgo.auth.application.store.dto.ReissueStoreAccountRequest;
import lombok.RequiredArgsConstructor;

@Tag(name = "Store Auth", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/store-accounts")
@RestController
public class StoreAuthControllerV1 {

	private final StoreAccountService storeAccountService;

	@Schema(description = "invalid:400, duplicated:409")
	@GetMapping("/login-id/valid")
	void validLoginId(String loginId) {
		storeAccountService.validLoginId(loginId);
	}

	@Hidden
	@PostMapping
	public void registerStore(@Valid @RequestBody RegisterStoreAccountRequest request) {
		storeAccountService.registerStoreAccount(request);
	}

	@Hidden
	@PreAuthorize("hasRole('STORE')")
	@DeleteMapping("/me")
	public void deleteStoreAccount(@AuthenticationPrincipal UUID storeId) {
		storeAccountService.deleteStoreAccount(storeId);
	}

	@Operation(summary = "가게 계정 로그인")
	@PostMapping("/login")
	public JwtSetResponse loginStoreAccount(@Valid @RequestBody LoginStoreAccountRequest request) {
		return storeAccountService.loginStoreAccount(request);
	}

	@Operation(summary = "가게 계정 로그인 갱신")
	@PostMapping("/reissue")
	public JwtSetResponse refreshStoreAccount(
		@Valid @RequestBody ReissueStoreAccountRequest request) {
		return storeAccountService.reissueStoreAccount(request);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{storeId}/active")
	void activeStoreAccount(@PathVariable UUID storeId) {
		storeAccountService.activeStoreAccount(storeId);
	}
}
