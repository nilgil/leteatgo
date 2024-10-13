package kr.co.leteatgo.geo.adapter.in.web.kakao;

import io.swagger.v3.oas.annotations.Parameter;

public record SearchByAddressRequest(
	@Parameter(required = true) String query,
	String analyzeType,
	Integer page,
	Integer size
) {

}
