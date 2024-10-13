package kr.co.leteatgo.store.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddMenuGroupRequest(
	@NotBlank String name,
	@NotNull Integer sort
) {

}
