package kr.co.leteatgo.etc.application.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.leteatgo.etc.domain.post.Notice;
import lombok.Builder;

@Builder
public record WriteNoticeRequest(
	@NotBlank String title,
	@NotNull Boolean visible,
	@NotNull Boolean hasBannerImage,
	String content
) {

	public Notice toEntity(String bannerImageUrl) {
		return Notice.builder()
			.title(title)
			.visible(visible)
			.bannerImage(bannerImageUrl)
			.content(content)
			.build();
	}
}
