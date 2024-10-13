package kr.co.leteatgo.user.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ChangeProfileImageRequest(
	@Schema(example = "test.jpg") String profileImage
) {

}
