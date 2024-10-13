package kr.co.leteatgo.geo.application.port.in;

import org.springframework.data.domain.Pageable;

import jakarta.validation.constraints.NotNull;
import kr.co.leteatgo.geo.common.SelfValidating;
import kr.co.leteatgo.geo.domain.Coordinate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchNearlyZonesCommand extends SelfValidating<SearchNearlyZonesCommand> {

	@NotNull
	private final Pageable pageable;
	@NotNull
	private final Coordinate coordinate;
	private final String search;

	@Builder
	private SearchNearlyZonesCommand(Pageable pageable, Coordinate coordinate, String search) {
		this.pageable = pageable;
		this.coordinate = coordinate;
		this.search = search;
		validateSelf();
	}
}
