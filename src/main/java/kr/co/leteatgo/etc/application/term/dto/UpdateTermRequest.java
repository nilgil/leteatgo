package kr.co.leteatgo.etc.application.term.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTermRequest(
	@NotBlank String title,
	@NotNull Boolean required,
	@NotNull Integer sort,
	Long parentId,
	String content
) {

}
