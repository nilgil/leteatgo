package kr.co.leteatgo.etc.application.post.dto;

import kr.co.leteatgo.etc.domain.post.MainBanner;
import kr.co.leteatgo.etc.domain.post.MainBannerType;
import lombok.Builder;

@Builder
public record BannerResponse(
	MainBannerType type,
	Long id,
	Boolean visible,
	String bannerImage
) {

	public static BannerResponse from(MainBanner mainBanner) {
		return BannerResponse.builder()
			.type(mainBanner.getBannerType())
			.id(mainBanner.getBannerId())
			.visible(mainBanner.visible())
			.bannerImage(mainBanner.getBannerImage())
			.build();
	}
}
