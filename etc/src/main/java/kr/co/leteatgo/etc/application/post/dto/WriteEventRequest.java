package kr.co.leteatgo.etc.application.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import kr.co.leteatgo.etc.domain.post.Event;

public record WriteEventRequest(
    @NotBlank String title,
    @NotNull Boolean visible,
    @NotNull LocalDateTime startDateTime,
    @NotNull LocalDateTime endDateTime,
    @NotNull Boolean hasBannerImage,
    @NotNull Integer contentImagesCount
) {

  public Event toEntity(String bannerImageUrl, List<String> contentImageUrls) {
    return Event.builder()
        .title(title)
        .visible(visible)
        .bannerImage(bannerImageUrl)
        .startDateTime(startDateTime)
        .endDateTime(endDateTime)
        .contentImages(contentImageUrls)
        .build();
  }
}
