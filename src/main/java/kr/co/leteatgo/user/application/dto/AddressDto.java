package kr.co.leteatgo.user.application.dto;

import kr.co.leteatgo.user.domain.Address;
import lombok.Builder;

@Builder
public record AddressDto(
	String regionAddress,
	String roadAddress,
	String locationName,
	String detail
) {

	public static AddressDto from(Address address) {
		return AddressDto.builder()
			.regionAddress(address.getRegionAddress())
			.roadAddress(address.getRoadAddress())
			.locationName(address.getLocationName())
			.detail(address.getDetail())
			.build();
	}
}
