package kr.co.leteatgo.etc.domain.post;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import kr.co.leteatgo.etc.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = BaseEntity.class)
@Audited(withModifiedFlag = true)
@MappedSuperclass
public abstract class Banners extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean visible;
	private String bannerImage;

	public Banners(Boolean visible, String bannerImage) {
		this.visible = visible;
		this.bannerImage = bannerImage;
	}

	public boolean visible() {
		return visible;
	}

	public String bannerImage() {
		return bannerImage;
	}

	public void changeVisible(Boolean visible) {
		this.visible = visible;
	}

	public void changeBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}
}
