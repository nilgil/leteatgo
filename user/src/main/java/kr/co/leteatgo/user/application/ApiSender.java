package kr.co.leteatgo.user.application;

import common.aws.s3.PreSignedUrlRequest;
import common.aws.s3.PreSignedUrls;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.function.Consumer;
import kr.co.leteatgo.user.application.dto.RegisterUserAccountMsg;
import kr.co.leteatgo.user.application.dto.ResetUserLocationsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ApiSender {

  private final HttpServletRequest request;
  private final WebClient authWebClient;
  private final WebClient etcWebClient;
  private final WebClient geoWebClient;

  public PreSignedUrls API$createPreSignedUrls(PreSignedUrlRequest message) {
    return etcWebClient.post().uri("/v1/aws/s3/pre-signed-urls")
        .bodyValue(message)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<PreSignedUrls>() {
        }).block();
  }

  public void AUTH$registerUserAccount(RegisterUserAccountMsg message) {
    authWebClient.post().uri("/v1/user-accounts")
        .bodyValue(message)
        .retrieve()
        .bodyToMono(Void.class).block();
  }

  public void AUTH$deleteUserAccount() {
    authWebClient.delete().uri("/v1/user-accounts/me")
        .headers(copyAuthorization())
        .retrieve()
        .bodyToMono(Void.class).block();
  }

  public void GEO$resetUserLocations(ResetUserLocationsMessage message) {
    geoWebClient.put().uri("/v1/user-locations")
        .bodyValue(message)
        .retrieve()
        .bodyToMono(Void.class).block();
  }

  public void GEO$deleteUserLocations(UUID userId) {
    geoWebClient.delete().uri("/v1/user-locations?userId={userId}", userId)
        .retrieve()
        .bodyToMono(Void.class)
        .subscribe();
  }

  private Consumer<HttpHeaders> copyAuthorization() {
    return httpHeaders -> httpHeaders.add(
        HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
  }
}
