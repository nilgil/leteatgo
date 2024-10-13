package kr.co.leteatgo.geo.adapter.in.web.kakao;

import io.swagger.v3.oas.annotations.Parameter;
import kr.co.leteatgo.geo.application.dto.CategoryGroupCode;

public record SearchByKeywordRequest(
	@Parameter(required = true) String query,
	@Parameter(name = "category_group_code") CategoryGroupCode categoryGroupCode,
	String x,
	String y,
	Integer radius,
	Integer rect,
	Integer page,
	Integer size,
	String sort
) {

}
