package kr.co.leteatgo.etc.application.post.dto;

import java.time.LocalDateTime;

import kr.co.leteatgo.etc.domain.post.Event;
import lombok.Builder;

@Builder
public record SimpleEventResponse(
	Long id,
	String title,
	Boolean visible,
	String bannerImage,
	LocalDateTime startDateTime,
	LocalDateTime endDateTime
) {

	public static SimpleEventResponse from(Event event) {
		return SimpleEventResponse.builder()
			.id(event.getId())
			.title(event.getTitle())
			.visible(event.visible())
			.startDateTime(event.getStartDateTime())
			.endDateTime(event.getEndDateTime())
			.bannerImage(event.getBannerImage())
			.build();
	}
}
