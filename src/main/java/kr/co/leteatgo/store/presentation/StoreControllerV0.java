package kr.co.leteatgo.store.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.store.application.dto.BusinessHourDto;
import kr.co.leteatgo.store.application.dto.CoordinateDto;
import kr.co.leteatgo.store.application.dto.StoreDetailResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Deprecated
@Tag(name = "Store", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/stores")
@RestController
public class StoreControllerV0 {

	private final StoreControllerV1 storeController;

	@GetMapping("/{storeId}")
	StoreDetailResponseV0 getStoreDetail(@PathVariable UUID storeId) {
		StoreDetailResponse store = storeController.getStoreDetail(storeId);
		return StoreDetailResponseV0.builder()
			.id(store.storeId())
			.images(store.images())
			.name(store.storeName())
			.viewCount(store.viewCount())
			.phone(store.phoneNumber())
			.coordinate(store.coordinate())
			.businessHours(store.businessHours())
			.dayOff(store.dayOff())
			.address(store.address())
			.event(store.event())
			.build();
	}

	@GetMapping("/{storeId}/menus")
	List<MenuGroupResponseV0> getStoreMenuSet(@PathVariable UUID storeId) {
		return storeController.getStoreMenuSet(storeId).groups().stream()
			.map(group -> MenuGroupResponseV0.builder()
				.id(group.id())
				.name(group.name())
				.menus(group.items().stream()
					.map(item -> MenuItemResponseV0.builder()
						.id(item.id())
						.name(item.name())
						.description(item.description())
						.price(item.price())
						.build()
					).toList()
				).build()
			).toList();
	}

	@GetMapping("/{storeId}/distance")
	Integer getDistanceFromStore(
		@PathVariable UUID storeId,
		@RequestParam Double lng,
		@RequestParam Double lat
	) {
		return storeController.getDistanceFromStore(storeId, lng, lat);
	}

	@Builder
	private record StoreSimpleResponseV0(UUID id, String mainImage, Integer maxPriceGap,
										 String name, String town, Integer distance, Long viewCount,
										 String event) {

	}

	@Builder
	private record StoreDetailResponseV0(UUID id, List<String> images, String name, Long viewCount,
										 String phone, CoordinateDto coordinate,
										 List<BusinessHourDto> businessHours, String dayOff,
										 String address, String event) {

	}

	@Builder
	private record MenuGroupResponseV0(Long id, String name, List<MenuItemResponseV0> menus) {

	}

	@Builder
	private record MenuItemResponseV0(Long id, String name, String description, Integer price) {

	}
}
