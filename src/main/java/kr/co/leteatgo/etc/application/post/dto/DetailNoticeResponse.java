package kr.co.leteatgo.etc.application.post.dto;

import java.time.LocalDateTime;

import kr.co.leteatgo.etc.domain.post.Notice;
import lombok.Builder;

@Builder
public record DetailNoticeResponse(
	Long id,
	String title,
	Boolean visible,
	String bannerImage,
	String content,
	LocalDateTime createdAt
) {

	public static DetailNoticeResponse from(Notice notice) {
		return DetailNoticeResponse.builder()
			.id(notice.getId())
			.title(notice.getTitle())
			.visible(notice.visible())
			.bannerImage(notice.getBannerImage())
			.content(notice.getContent())
			.createdAt(notice.getCreatedAt())
			.build();
	}
}
