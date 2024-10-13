package kr.co.leteatgo.store.application.dto;

import org.springframework.util.StringUtils;

public record AddressDto(
	String regionAddress,
	String roadAddress,
	String locationName,
	String detail
) {

	public AddressDto {
		if (!StringUtils.hasText(regionAddress) && !StringUtils.hasText(roadAddress)) {
			throw new IllegalArgumentException("regionAddress or roadAddress must not be blank");
		}
	}
}
