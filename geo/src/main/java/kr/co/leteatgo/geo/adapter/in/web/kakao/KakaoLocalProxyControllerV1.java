package kr.co.leteatgo.geo.adapter.in.web.kakao;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.geo.application.port.in.KakaoLocalProxyUseCase;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "KakaoLocal Api Proxy", description = "v1")
@RequestMapping("/v1/proxy/kakao-local")
@RequiredArgsConstructor
@RestController
public class KakaoLocalProxyControllerV1 {

  private final KakaoLocalProxyUseCase kakaoLocalProxyUseCase;

  @GetMapping("/address")
  Mono<Resource> getAddressesByAddress(@ParameterObject SearchByAddressRequest request) {
    return kakaoLocalProxyUseCase.getAddressesByAddress(request);
  }

  @GetMapping("/keyword")
  Mono<Resource> getAddressesByKeyword(@ParameterObject SearchByKeywordRequest request) {
    return kakaoLocalProxyUseCase.getAddressesByKeyword(request);
  }
}
