package kr.co.leteatgo.store.domain.store.value;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.store.domain.common.ImagePath;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StoreImages {

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "main_image"))
	private ImagePath mainImage;
	@ElementCollection
	@AttributeOverride(name = "value", column = @Column(name = "image"))
	private List<ImagePath> images;

	public StoreImages(ImagePath mainImage, List<ImagePath> images) {
		this.mainImage = mainImage;
		this.images = images;
	}

	public static StoreImages init() {
		return new StoreImages(null, new ArrayList<>());
	}

	public void updateMainImage(ImagePath mainImage) {
		if (mainImage == null) {
			throw new LegException(ErrorCode.BAD_REQUEST, "메인 이미지는 필수입니다.");
		}
		this.mainImage = mainImage;
	}

	public void updateSubImages(List<ImagePath> images) {
		if (4 < images.size()) {
			throw new LegException(ErrorCode.BAD_REQUEST, "서브 이미지는 최대 4개까지 등록 가능합니다.");
		}
		this.images = images;
	}
}
