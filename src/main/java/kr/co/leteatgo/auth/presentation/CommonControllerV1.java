package kr.co.leteatgo.auth.presentation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.auth.application.authcode.AuthCodeService;
import kr.co.leteatgo.auth.application.authcode.dto.MatchAuthCodeRequest;
import kr.co.leteatgo.auth.application.authcode.dto.SendAuthCodeRequest;
import kr.co.leteatgo.auth.application.common.ChangeTokenExpirationTimeRequest;
import kr.co.leteatgo.auth.application.jwt.JwtGenerator;
import lombok.RequiredArgsConstructor;

@Tag(name = "Common Auth", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/common")
@RestController
public class CommonControllerV1 {

	private final AuthCodeService authCodeService;
	private final JwtGenerator jwtGenerator;

	@Operation(summary = "인증 코드 전송")
	@PostMapping("/auth-code")
	public void sendAuthCode(@Valid @RequestBody SendAuthCodeRequest request) {
		authCodeService.sendAuthCode(request.phoneNumber());
	}

	@Operation(summary = "인증 코드 매칭")
	@PostMapping("/auth-code/match")
	public Boolean matchAuthCode(@Valid @RequestBody MatchAuthCodeRequest request) {
		return authCodeService.matchAuthCode(request.phoneNumber(), request.authCode());
	}

	@Hidden
	@Operation(summary = "토큰 만료 기간 변경")
	@PreAuthorize("hasRole('SUPER')")
	@PostMapping("/token-expiration")
	public void changeTokenExpirationTime(
		@Valid @RequestBody ChangeTokenExpirationTimeRequest request) {
		jwtGenerator.changeDefaultExpirationTime(request.tokenType(), request.expirationTime());
	}
}
