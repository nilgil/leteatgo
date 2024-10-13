package kr.co.leteatgo.notification.application.sms;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.function.Consumer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;
import kr.co.leteatgo.notification.application.sms.dto.MessageDto;
import kr.co.leteatgo.notification.application.sms.dto.SendNaverSmsDto.Request;
import kr.co.leteatgo.notification.application.sms.dto.SendSmsRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Profile("!local")
@Component
public class NaverSmsService implements SmsService {

	private static final String DEFAULT_SIGNING_ALGORITHM = "HmacSHA256";
	private static final String NAVER_SMS_HOST = "https://sens.apigw.ntruss.com";
	private static final String SMS_API_URI = "/sms/v2/services/";
	private static final String MESSAGES_API_URI = "/messages";
	private static final String SPACE = " ";
	private static final String ENTER = "\n";
	private static final String TYPE = "SMS";
	private static final String FROM = "07080650451";
	private final WebClient webClient;
	@Value("${naver.account.access-key}")
	private String accessKey;
	@Value("${naver.account.secret-key}")
	private String secretKey;
	@Value("${naver.sms.service-id}")
	private String serviceId;
	private Mac mac;

	@PostConstruct
	void postConstruct() {
		try {
			byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
			SecretKeySpec signingKey = new SecretKeySpec(secretKeyBytes, DEFAULT_SIGNING_ALGORITHM);
			Mac mac = Mac.getInstance(DEFAULT_SIGNING_ALGORITHM);
			mac.init(signingKey);
			this.mac = mac;
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException("서버 오류");
		}
	}

	@Override
	public void sendSms(SendSmsRequest request) {
		URI uri = URI.create(NAVER_SMS_HOST + SMS_API_URI + serviceId + MESSAGES_API_URI);

		List<MessageDto> toList = request.phoneNumbers().stream()
			.map(phoneNumber -> phoneNumber.replace("-", ""))
			.map(MessageDto::new)
			.toList();
		Request sendRequest = new Request(TYPE, FROM, toList, request.content());

		webClient.post()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.headers(makeHttpHeaders())
			.bodyValue(sendRequest)
			.retrieve()
			.bodyToMono(Void.class)
			.subscribe();
	}

	private Consumer<HttpHeaders> makeHttpHeaders() {
		return httpHeaders -> {
			String timestamp = currentTimestamp();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.set("x-ncp-apigw-signature-v2", makeSignature(timestamp));
			httpHeaders.set("x-ncp-apigw-timestamp", timestamp);
			httpHeaders.set("x-ncp-iam-access-key", accessKey);
		};
	}

	private String currentTimestamp() {
		return String.valueOf(System.currentTimeMillis());
	}

	private String makeSignature(String timestamp) {
		String message = makeSignatureMessage(timestamp);
		byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
		return Base64.encodeBase64String(rawHmac);
	}

	private String makeSignatureMessage(String timestamp) {
		String url = SMS_API_URI + serviceId + MESSAGES_API_URI;
		return HttpMethod.POST + SPACE + url + ENTER + timestamp + ENTER + accessKey;
	}
}
