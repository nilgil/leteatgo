package kr.co.leteatgo.etc.domain.post;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = Banners.class)
@Audited(withModifiedFlag = true)
@Entity
public class Event extends Banners {

  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private String title;
//  @Fetch(FetchMode.SUBSELECT)
  @ElementCollection
  private List<String> contentImages = new ArrayList<>();

  @Builder
  private Event(Boolean visible, String bannerImage, LocalDateTime startDateTime,
      LocalDateTime endDateTime, String title, List<String> contentImages) {
    super(visible, bannerImage);
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.title = title;
    this.contentImages = contentImages;
  }
}
