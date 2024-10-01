package kr.co.leteatgo.auth.application.client;

import common.auth.ApiKey;
import java.util.UUID;
import kr.co.leteatgo.auth.application.client.dto.IssueApiKeyResponse;
import kr.co.leteatgo.auth.application.client.dto.RegisterClientRequest;
import kr.co.leteatgo.auth.application.client.dto.JwtTokenResponse;

public interface ClientAccountService {

  void registerClientAccount(RegisterClientRequest request);

  void deleteClientAccount(UUID clientId);

  IssueApiKeyResponse issueApiKey(UUID clientId);

  JwtTokenResponse verifyApiKey(UUID clientId, ApiKey apiKey);
}
