package kr.co.leteatgo.store.domain.store.value;

import org.springframework.util.StringUtils;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

	private String regionAddress;
	private String roadAddress;
	private String locationName;
	private String detail;

	@Builder
	private Address(String regionAddress, String roadAddress, String locationName, String detail) {
		this.regionAddress = regionAddress;
		this.roadAddress = roadAddress;
		this.locationName = locationName;
		this.detail = detail;
	}

	public String fullAddress() {
		String fullAddress;
		if (StringUtils.hasText(roadAddress)) {
			fullAddress = roadAddress;
		} else {
			fullAddress = regionAddress;
		}
		if (StringUtils.hasText(detail)) {
			fullAddress += " " + detail;
		}
		return fullAddress;
	}
}
