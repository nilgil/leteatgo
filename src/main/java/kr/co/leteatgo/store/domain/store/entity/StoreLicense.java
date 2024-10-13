package kr.co.leteatgo.store.domain.store.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import kr.co.leteatgo.store.domain.common.ImagePath;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreLicense extends BaseIdEntity {

	@Embedded
	private LicenseNumber licenseNumber;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "license_image"))
	private ImagePath licenseImage;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "business_permit_image"))
	private ImagePath businessPermitImage;
	private String businessType;
	private String industry;

	@Builder
	private StoreLicense(LicenseNumber licenseNumber, ImagePath licenseImage,
		ImagePath businessPermitImage, String businessType, String industry) {
		this.licenseNumber = licenseNumber;
		this.licenseImage = licenseImage;
		this.businessPermitImage = businessPermitImage;
		this.businessType = businessType;
		this.industry = industry;
	}
}
