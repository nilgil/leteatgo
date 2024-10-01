package kr.co.leteatgo.geo.adapter.out.api;

import static java.util.Optional.ofNullable;

import jakarta.annotation.PostConstruct;
import kr.co.leteatgo.geo.application.dto.CategoryGroupCode;
import kr.co.leteatgo.geo.application.port.out.KakaoLocalApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class KakaoLocalApiWebClientAdapter implements KakaoLocalApiPort {

  private WebClient webClient;

  @Value("${kakao.credential.rest-api-key}")
  private String kakaoSecretKey;

  @PostConstruct
  public void setup() {
    webClient = WebClient.builder().baseUrl("https://dapi.kakao.com/v2/local/search")
        .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoSecretKey)
        .build();
  }

  @Override
  public Mono<Resource> getAddressesByAddress(String query, String analyzeType, Integer page,
      Integer size) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/address.json")
            .queryParamIfPresent("query", ofNullable(query))
            .queryParamIfPresent("analyze_type", ofNullable(analyzeType))
            .queryParamIfPresent("page", ofNullable(page))
            .queryParamIfPresent("size", ofNullable(size))
            .build())
        .retrieve()
        .bodyToMono(Resource.class);
  }

  @Override
  public Mono<Resource> getAddressesByKeyword(String query, CategoryGroupCode categoryGroupCode,
      String x, String y, Integer radius, Integer rect, Integer page, Integer size, String sort) {
    return webClient.get().uri(uriBuilder -> uriBuilder
            .path("/keyword.json")
            .queryParamIfPresent("query", ofNullable(query))
            .queryParamIfPresent("category_group_code", ofNullable(categoryGroupCode))
            .queryParamIfPresent("x", ofNullable(x))
            .queryParamIfPresent("y", ofNullable(y))
            .queryParamIfPresent("radius", ofNullable(radius))
            .queryParamIfPresent("rect", ofNullable(rect))
            .queryParamIfPresent("page", ofNullable(page))
            .queryParamIfPresent("size", ofNullable(size))
            .queryParamIfPresent("sort", ofNullable(sort))
            .build())
        .retrieve()
        .bodyToMono(Resource.class);
  }
}
