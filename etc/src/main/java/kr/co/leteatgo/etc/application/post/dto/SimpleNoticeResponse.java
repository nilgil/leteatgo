package kr.co.leteatgo.etc.application.post.dto;

import java.time.LocalDateTime;
import kr.co.leteatgo.etc.domain.post.Notice;
import lombok.Builder;

@Builder
public record SimpleNoticeResponse(
    Long id,
    String title,
    Boolean visible,
    String bannerImage,
    LocalDateTime createdAt
) {

  public static SimpleNoticeResponse from(Notice notice) {
    return SimpleNoticeResponse.builder()
        .id(notice.getId())
        .title(notice.getTitle())
        .visible(notice.visible())
        .bannerImage(notice.getBannerImage())
        .createdAt(notice.getCreatedAt())
        .build();
  }
}
