package kr.co.leteatgo.etc.domain.post;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = Banners.class)
@Audited(withModifiedFlag = true)
@Entity
public class Notice extends Banners {

	private String title;
	@Column(length = 10000)
	private String content;

	@Builder
	private Notice(Boolean visible, String bannerImage, String title, String content) {
		super(visible, bannerImage);
		this.title = title;
		this.content = content;
	}
}
