package kr.co.leteatgo.auth.application.common;

import kr.co.leteatgo.auth.application.authcode.dto.SendSmsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class ApiSender {

  private final WebClient notiWebClient;

  public void NOTI$sendSms(SendSmsMessage message) {
    notiWebClient.post()
        .uri("/v1/sms")
        .bodyValue(message)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }
}
