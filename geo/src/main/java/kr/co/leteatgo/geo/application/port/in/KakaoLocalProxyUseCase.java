package kr.co.leteatgo.geo.application.port.in;

import kr.co.leteatgo.geo.adapter.in.web.kakao.SearchByAddressRequest;
import kr.co.leteatgo.geo.adapter.in.web.kakao.SearchByKeywordRequest;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

public interface KakaoLocalProxyUseCase {

  Mono<Resource> getAddressesByAddress(SearchByAddressRequest reqDto);

  Mono<Resource> getAddressesByKeyword(SearchByKeywordRequest reqDto);
}
