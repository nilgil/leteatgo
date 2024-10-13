package kr.co.leteatgo.geo.adapter.in.web.zone;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.geo.application.dto.ZoneResponse;
import kr.co.leteatgo.geo.application.port.in.SearchNearlyZonesCommand;
import kr.co.leteatgo.geo.application.port.in.ZoneUseCase;
import kr.co.leteatgo.geo.common.Scroll;
import kr.co.leteatgo.geo.domain.Coordinate;
import lombok.RequiredArgsConstructor;

@Tag(name = "Zones Api", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/zones")
@RestController
public class ZoneControllerV1 {

	private final ZoneUseCase zoneUseCase;

	@Operation(summary = "동네 검색 (가까운 순) ")
	@GetMapping
	public Scroll<ZoneResponse> searchNearlyZones(
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "10") Integer size,
		@RequestParam(required = false) String search,
		Double lng, Double lat
	) {
		SearchNearlyZonesCommand command = SearchNearlyZonesCommand.builder()
			.pageable(PageRequest.of(page, size))
			.coordinate(Coordinate.of(lng, lat))
			.search(search)
			.build();
		return zoneUseCase.searchNearlyZones(command).map(ZoneResponse::from);
	}

	@Operation(summary = "해당 위치가 소속된 동네 반환")
	@GetMapping("/contain")
	public ZoneResponse findContainZone(Double lng, Double lat) {
		return ZoneResponse.from(zoneUseCase.findContainZone(Coordinate.of(lng, lat)));
	}

	@Hidden
	@PreAuthorize("hasRole('SUPER')")
	@PostMapping("/init-data")
	public Integer resetZonesByGeoJson(MultipartFile zoneData) {
		return zoneUseCase.resetZoneData(zoneData);
	}
}
