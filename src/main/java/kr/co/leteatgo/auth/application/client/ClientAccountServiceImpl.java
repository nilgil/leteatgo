package kr.co.leteatgo.auth.application.client;

import java.time.Duration;
import java.util.UUID;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.auth.application.client.dto.IssueApiKeyResponse;
import kr.co.leteatgo.auth.application.client.dto.JwtTokenResponse;
import kr.co.leteatgo.auth.application.client.dto.RegisterClientRequest;
import kr.co.leteatgo.auth.application.common.CredentialService;
import kr.co.leteatgo.auth.application.jwt.JwtGenerator;
import kr.co.leteatgo.auth.domain.apikey.ApiKeyUtils;
import kr.co.leteatgo.auth.domain.client.ClientAccount;
import kr.co.leteatgo.auth.domain.client.ClientAccountRepository;
import kr.co.leteatgo.common.auth.ApiKey;
import kr.co.leteatgo.common.auth.jwt.JwtToken;
import kr.co.leteatgo.common.auth.jwt.TokenType;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "apiKey")
@RequiredArgsConstructor
public class ClientAccountServiceImpl implements ClientAccountService {

	private static final Duration CLIENT_ACCESS_TOKEN_EXP_TIME = Duration.ofMinutes(30);

	private final ClientAccountMapper clientAccountMapper;
	private final ClientAccountRepository clientAccountRepository;
	private final CredentialService credentialService;
	private final JwtGenerator jwtGenerator;

	@Transactional
	@Override
	public void registerClientAccount(RegisterClientRequest request) {
		ClientAccount clientAccount = clientAccountMapper.create(request);
		validDuplicatedName(clientAccount.getName());

		clientAccountRepository.save(clientAccount);
	}

	@Transactional
	@Override
	public void deleteClientAccount(UUID clientId) {
		ClientAccount clientAccount = getClientAccountById(clientId);
		clientAccountRepository.delete(clientAccount);
	}

	@Transactional
	@CacheEvict(value = "apiKeyToJwt", key = "#clientId")
	@Override
	public IssueApiKeyResponse issueApiKey(UUID clientId) {
		ClientAccount clientAccount = getClientAccountById(clientId);
		clientAccount.validAccount();

		ApiKey apiKey = ApiKeyUtils.generate(clientId);
		clientAccount.updateApiKey(apiKey.value());

		return new IssueApiKeyResponse(apiKey);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "apiKeyToJwt", key = "#clientId")
	@Override
	public JwtTokenResponse verifyApiKey(UUID clientId, ApiKey apiKey) {
		ClientAccount clientAccount = getClientAccountById(clientId);
		credentialService.validApiKey(clientAccount, apiKey);
		clientAccount.validAccount();

		JwtToken accessToken = jwtGenerator.generateJwtToken(TokenType.ACCESS,
			clientAccount, CLIENT_ACCESS_TOKEN_EXP_TIME);

		return new JwtTokenResponse(accessToken);
	}

	private void validDuplicatedName(String name) {
		clientAccountRepository.findByName(name).ifPresent(clientAccount -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated name");
		});
	}

	private ClientAccount getClientAccountById(UUID clientId) {
		return clientAccountRepository.findById(clientId)
			.orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
	}
}
