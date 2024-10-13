package kr.co.leteatgo.store.application.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import kr.co.leteatgo.common.aws.s3.AwsS3Folder;
import kr.co.leteatgo.common.aws.s3.PreSignedUrlRequest;
import kr.co.leteatgo.common.aws.s3.PreSignedUrls;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.store.application.dto.BankAccountDto;
import kr.co.leteatgo.store.application.dto.TownDto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LegApiClient {

	private final WebClient authWebClient;
	private final WebClient bankWebClient;
	private final WebClient geoWebClient;
	private final WebClient etcWebClient;

	public TownDto getContainTown(Double lng, Double lat) {
		Map<String, String> response = geoWebClient.get()
			.uri(uriBuilder -> uriBuilder.path("/v1/zones/contain")
				.queryParam("lng", lng)
				.queryParam("lat", lat)
				.build())
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
			}).block();
		return new TownDto(response.get("code"), response.get("town"));
	}

	public PreSignedUrls createPreSignedUrls(AwsS3Folder folder, Integer count) {
		if (count == null || count < 1) {
			return null;
		}
		return etcWebClient.post()
			.uri("/v1/aws/s3/pre-signed-urls")
			.bodyValue(new PreSignedUrlRequest(folder, count))
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<PreSignedUrls>() {
			})
			.blockOptional()
			.orElseThrow(() -> new LegException(ErrorCode.INTERNAL_SERVER_ERROR));
	}

	public PreSignedUrls registerBankAccount(UUID storeId, BankAccountDto bankAccountDto) {
		RegisterBankAccountRequest request = new RegisterBankAccountRequest(storeId,
			bankAccountDto.bankName(), bankAccountDto.accountHolder(), bankAccountDto.accountNumber());
		return bankWebClient.post()
			.uri("/v1/bank-accounts")
			.bodyValue(request)
			.retrieve()
			.bodyToMono(PreSignedUrls.class)
			.block();
	}

	public void registerStoreAccount(UUID storeId, String loginId, String loginPwd) {
		authWebClient.post()
			.uri("/v1/store-accounts")
			.bodyValue(new RegisterAccountRequest(storeId, loginId, loginPwd))
			.retrieve()
			.bodyToMono(Void.class)
			.block();
	}

	private record RegisterBankAccountRequest(UUID accountId, String bankName, String accountHolder,
											  String accountNumber) {

	}

	record RegisterAccountRequest(UUID storeId, String loginId, String loginPwd) {

	}
}
