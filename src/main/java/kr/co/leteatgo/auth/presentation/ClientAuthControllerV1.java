package kr.co.leteatgo.auth.presentation;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.auth.application.client.ClientAccountService;
import kr.co.leteatgo.auth.application.client.dto.IssueApiKeyResponse;
import kr.co.leteatgo.auth.application.client.dto.JwtTokenResponse;
import kr.co.leteatgo.auth.application.client.dto.RegisterClientRequest;
import kr.co.leteatgo.auth.application.client.dto.VerifyApiKeyRequest;
import kr.co.leteatgo.auth.domain.apikey.ApiKeyUtils;
import kr.co.leteatgo.common.auth.ApiKey;
import lombok.RequiredArgsConstructor;

@Hidden
@Tag(name = "Client Auth", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/client-accounts")
@RestController
public class ClientAuthControllerV1 {

	private final ClientAccountService clientAccountService;

	@Operation(summary = "API 클라이언트 계정 추가")
	@PreAuthorize("hasRole('SUPER')")
	@PostMapping
	public void registerClientAccount(@Valid @RequestBody RegisterClientRequest request) {
		clientAccountService.registerClientAccount(request);
	}

	@Operation(summary = "API 클라이언트 계정 삭제")
	@PreAuthorize("hasRole('SUPER')")
	@DeleteMapping("/{clientId}")
	public void deleteClientAccount(@PathVariable UUID clientId) {
		clientAccountService.deleteClientAccount(clientId);
	}

	@Operation(summary = "API 클라이언트 api key 발급")
	@PreAuthorize("hasRole('SUPER')")
	@PostMapping("/{clientId}/issue-ak")
	public IssueApiKeyResponse issueApiKey(@PathVariable UUID clientId) {
		return clientAccountService.issueApiKey(clientId);
	}

	@Hidden
	@Operation(summary = "API 클라이언트 api key 검증")
	@PostMapping("/verify-ak")
	public JwtTokenResponse verifyApiKey(@Valid @RequestBody VerifyApiKeyRequest request) {
		ApiKey apiKey = ApiKey.valueOf(request.apiKey());
		UUID clientId = ApiKeyUtils.parse(apiKey);
		return clientAccountService.verifyApiKey(clientId, apiKey);
	}
}
