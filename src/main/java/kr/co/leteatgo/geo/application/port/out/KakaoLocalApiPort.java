package kr.co.leteatgo.geo.application.port.out;

import org.springframework.core.io.Resource;

import kr.co.leteatgo.geo.application.dto.CategoryGroupCode;
import reactor.core.publisher.Mono;

public interface KakaoLocalApiPort {

	Mono<Resource> getAddressesByAddress(String query, String analyzeType, Integer page,
		Integer size);

	Mono<Resource> getAddressesByKeyword(String query, CategoryGroupCode categoryGroupCode,
		String x, String y, Integer radius, Integer rect, Integer page, Integer size, String sort);
}
