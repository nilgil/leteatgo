package kr.co.leteatgo.geo.adapter.out.api;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import kr.co.leteatgo.geo.application.dto.CategoryGroupCode;
import kr.co.leteatgo.geo.application.port.out.KakaoLocalApiPort;
import reactor.core.publisher.Mono;

@Profile("local")
@Component
public class NoOpKakaoLocalApiPort implements KakaoLocalApiPort {
	@Override
	public Mono<Resource> getAddressesByAddress(String query, String analyzeType, Integer page, Integer size) {
		return Mono.empty();
	}

	@Override
	public Mono<Resource> getAddressesByKeyword(String query, CategoryGroupCode categoryGroupCode, String x, String y,
		Integer radius, Integer rect, Integer page, Integer size, String sort) {
		return Mono.empty();
	}
}
