package kr.co.leteatgo.geo.adapter.in.web.zone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.geo.application.dto.ZoneResponse;
import kr.co.leteatgo.geo.common.Scroll;
import lombok.RequiredArgsConstructor;

@Deprecated
@Tag(name = "Zones Api", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/zones")
@RestController
public class ZoneControllerV0 {

	private final ZoneControllerV1 zoneController;

	@Operation(summary = "동네 검색 (가까운 순) ")
	@GetMapping
	public Scroll<ZoneResponseV0> searchNearlyZones(
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "10") Integer size,
		@RequestParam(required = false) String search,
		Double lng, Double lat
	) {
		return zoneController.searchNearlyZones(page, size, search, lng, lat).map(response ->
			new ZoneResponseV0(Integer.valueOf(response.getCode()),
				new ZoneAddressV0(
					response.getFullAddress(),
					response.getCity(),
					response.getSubCity(),
					response.getDistrict(),
					response.getTown()
				)));
	}

	@Operation(summary = "해당 위치가 소속된 동네 반환")
	@GetMapping("/contains")
	public ZoneResponseV0 getZoneByCoordinate(Double lng, Double lat) {
		ZoneResponse response = zoneController.findContainZone(lng, lat);
		return new ZoneResponseV0(Integer.valueOf(response.getCode()),
			new ZoneAddressV0(
				response.getFullAddress(),
				response.getCity(),
				response.getSubCity(),
				response.getDistrict(),
				response.getTown()
			));
	}

	public record ZoneResponseV0(Integer code, ZoneAddressV0 zoneAddress) {

	}

	public record ZoneAddressV0(String fullAddress, String city, String subCity, String district,
								String town) {

	}
}
