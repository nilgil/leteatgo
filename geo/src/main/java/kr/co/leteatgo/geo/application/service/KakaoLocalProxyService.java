package kr.co.leteatgo.geo.application.service;

import kr.co.leteatgo.geo.adapter.in.web.kakao.SearchByAddressRequest;
import kr.co.leteatgo.geo.adapter.in.web.kakao.SearchByKeywordRequest;
import kr.co.leteatgo.geo.application.port.in.KakaoLocalProxyUseCase;
import kr.co.leteatgo.geo.application.port.out.KakaoLocalApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KakaoLocalProxyService implements KakaoLocalProxyUseCase {

  private final KakaoLocalApiPort kakaoLocalApiPort;

  @Override
  public Mono<Resource> getAddressesByAddress(SearchByAddressRequest reqDto) {
    return kakaoLocalApiPort.getAddressesByAddress(reqDto.query(), reqDto.analyzeType(),
        reqDto.page(), reqDto.size());
  }

  @Override
  public Mono<Resource> getAddressesByKeyword(SearchByKeywordRequest reqDto) {
    return kakaoLocalApiPort.getAddressesByKeyword(reqDto.query(), reqDto.categoryGroupCode(),
        reqDto.x(), reqDto.y(), reqDto.radius(), reqDto.rect(), reqDto.page(), reqDto.size(),
        reqDto.sort());
  }
}
