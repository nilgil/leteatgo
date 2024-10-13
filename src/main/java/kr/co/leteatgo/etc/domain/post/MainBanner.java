package kr.co.leteatgo.etc.domain.post;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MainBanner extends Banners {

	private MainBannerType bannerType;
	private Long bannerId;

	public MainBanner(Event event) {
		super(event.visible(), event.bannerImage());
		this.bannerType = MainBannerType.EVENT;
		this.bannerId = event.getId();
	}

	public MainBanner(Notice notice) {
		super(notice.visible(), notice.bannerImage());
		this.bannerType = MainBannerType.NOTICE;
		this.bannerId = notice.getId();
	}
}
