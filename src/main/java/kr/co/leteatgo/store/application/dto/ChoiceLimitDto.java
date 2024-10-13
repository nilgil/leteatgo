package kr.co.leteatgo.store.application.dto;

import lombok.Builder;

@Builder
public record ChoiceLimitDto(
	Integer min,
	Integer max
) {

}
