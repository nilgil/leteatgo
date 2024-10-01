package kr.co.leteatgo.etc.application.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import kr.co.leteatgo.etc.domain.post.Event;
import lombok.Builder;

@Builder
public record DetailEventResponse(
    Long id,
    String title,
    Boolean visible,
    String bannerImage,
    LocalDateTime startDateTime,
    LocalDateTime endDateTime,
    List<String> contentImages
) {

  public static DetailEventResponse from(Event event) {
    return DetailEventResponse.builder()
        .id(event.getId())
        .title(event.getTitle())
        .visible(event.visible())
        .bannerImage(event.getBannerImage())
        .startDateTime(event.getStartDateTime())
        .endDateTime(event.getEndDateTime())
        .contentImages(event.getContentImages())
        .build();
  }
}
