package kr.co.leteatgo.user.application.mapper;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

import kr.co.leteatgo.user.application.GeoFactory;
import kr.co.leteatgo.user.application.dto.AddressDto;
import kr.co.leteatgo.user.application.dto.LocationDto;
import kr.co.leteatgo.user.domain.Address;
import kr.co.leteatgo.user.domain.Location;
import kr.co.leteatgo.user.domain.User;

@Component
public class LocationMapper {

	public Location create(LocationDto request, User user) {
		Address address = dtoToAddress(request.address());
		Point point = GeoFactory.lngLatToPoint(request.coordinate().lng(), request.coordinate().lat());
		return Location.builder()
			.alias(request.alias())
			.marked(request.marked())
			.active(true)
			.address(address)
			.point(point)
			.user(user)
			.build();
	}

	private Address dtoToAddress(AddressDto addressDto) {
		return Address.builder()
			.regionAddress(addressDto.regionAddress())
			.roadAddress(addressDto.roadAddress())
			.locationName(addressDto.locationName())
			.detail(addressDto.detail())
			.build();
	}
}
