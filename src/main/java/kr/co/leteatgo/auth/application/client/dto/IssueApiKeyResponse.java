package kr.co.leteatgo.auth.application.client.dto;

import kr.co.leteatgo.common.auth.ApiKey;

public record IssueApiKeyResponse(String apiKey) {

	public IssueApiKeyResponse(ApiKey apiKey) {
		this(apiKey.value());
	}
}
