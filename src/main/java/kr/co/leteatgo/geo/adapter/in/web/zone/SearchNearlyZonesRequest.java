package kr.co.leteatgo.geo.adapter.in.web.zone;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.leteatgo.geo.application.port.in.SearchNearlyZonesCommand;
import kr.co.leteatgo.geo.domain.Coordinate;

public record SearchNearlyZonesRequest(
	Integer page,
	Integer size,
	String search,
	Double lng,
	Double lat
) {

	public SearchNearlyZonesRequest(
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "10") Integer size,
		@RequestParam(required = false) String search,
		Double lng, Double lat
	) {
		this.page = page;
		this.size = size;
		this.search = search;
		this.lng = lng;
		this.lat = lat;
	}

	public SearchNearlyZonesCommand toCommand() {
		return SearchNearlyZonesCommand.builder()
			.pageable(PageRequest.of(page, size))
			.coordinate(Coordinate.of(lng, lat))
			.search(search)
			.build();
	}
}
