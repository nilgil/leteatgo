package kr.co.leteatgo.auth.application.client.dto;

import common.auth.ApiKey;

public record IssueApiKeyResponse(String apiKey) {

  public IssueApiKeyResponse(ApiKey apiKey) {
    this(apiKey.value());
  }
}
