package kr.co.leteatgo.auth.presentation;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import kr.co.leteatgo.auth.application.user.UserAccountService;
import kr.co.leteatgo.auth.application.user.dto.LoginUserAccountRequest;
import kr.co.leteatgo.auth.application.user.dto.RegisterUserAccountRequest;
import kr.co.leteatgo.auth.application.user.dto.ReissueUserAccountRequest;
import lombok.RequiredArgsConstructor;

@Tag(name = "User Auth", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/user-accounts")
@RestController
public class UserAuthControllerV1 {

	private final UserAccountService userAccountService;

	@GetMapping("/phone-number/valid")
	void validPhoneNumber(String phoneNumber) {
		userAccountService.validPhoneNumber(phoneNumber);
	}

	@Hidden
	@PostMapping
	public void registerUserAccount(@Valid @RequestBody RegisterUserAccountRequest request) {
		userAccountService.registerUserAccount(request);
	}

	@Hidden
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/me")
	public void deleteUserAccount(@AuthenticationPrincipal UUID userId) {
		userAccountService.deleteUserAccount(userId);
	}

	@Operation(summary = "유저 계정 로그인")
	@PostMapping("/login")
	public JwtSetResponse loginUserAccount(@Valid @RequestBody LoginUserAccountRequest request) {
		return userAccountService.loginUserAccount(request);
	}

	@Operation(summary = "유저 계정 로그인 갱신", description = "401: 등록되지 않은 RefreshToken , 403: 등록되지 않은 DeviceToken")
	@PostMapping("/reissue")
	public JwtSetResponse refreshUserAccount(@Valid @RequestBody ReissueUserAccountRequest request) {
		return userAccountService.reissueUserAccount(request);
	}
}
