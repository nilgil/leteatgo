package kr.co.leteatgo.store.application.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import kr.co.leteatgo.common.aws.s3.PreSignedUrl;
import kr.co.leteatgo.store.application.GeoFactory;
import kr.co.leteatgo.store.application.dto.AddMenuGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuItemOptionGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuItemRequest;
import kr.co.leteatgo.store.application.dto.AddMenuOptionGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuOptionItemRequest;
import kr.co.leteatgo.store.application.dto.AddressDto;
import kr.co.leteatgo.store.application.dto.BusinessHourDto;
import kr.co.leteatgo.store.application.dto.ChoiceLimitDto;
import kr.co.leteatgo.store.application.dto.ContactDto;
import kr.co.leteatgo.store.application.dto.CoordinateDto;
import kr.co.leteatgo.store.application.dto.ImageSubmissionResponse;
import kr.co.leteatgo.store.application.dto.LicenseDto;
import kr.co.leteatgo.store.application.dto.LocationDto;
import kr.co.leteatgo.store.application.dto.MenuGroupResponse;
import kr.co.leteatgo.store.application.dto.MenuItemResponse;
import kr.co.leteatgo.store.application.dto.MenuOptionGroupResponse;
import kr.co.leteatgo.store.application.dto.MenuOptionItemResponse;
import kr.co.leteatgo.store.application.dto.MenuSetResponse;
import kr.co.leteatgo.store.application.dto.RegisterStoreRequest;
import kr.co.leteatgo.store.application.dto.StoreDetailResponse;
import kr.co.leteatgo.store.application.dto.TermAgreementsDto;
import kr.co.leteatgo.store.application.dto.TownDto;
import kr.co.leteatgo.store.domain.common.Email;
import kr.co.leteatgo.store.domain.common.ImagePath;
import kr.co.leteatgo.store.domain.common.PhoneNumber;
import kr.co.leteatgo.store.domain.store.StoreImageSubmission;
import kr.co.leteatgo.store.domain.store.entity.LicenseNumber;
import kr.co.leteatgo.store.domain.store.entity.MenuGroup;
import kr.co.leteatgo.store.domain.store.entity.MenuItem;
import kr.co.leteatgo.store.domain.store.entity.MenuItemOptionGroup;
import kr.co.leteatgo.store.domain.store.entity.MenuOptionGroup;
import kr.co.leteatgo.store.domain.store.entity.MenuOptionItem;
import kr.co.leteatgo.store.domain.store.entity.Store;
import kr.co.leteatgo.store.domain.store.entity.StoreLicense;
import kr.co.leteatgo.store.domain.store.entity.StoreLocation;
import kr.co.leteatgo.store.domain.store.entity.StoreMenuSet;
import kr.co.leteatgo.store.domain.store.entity.StoreTermAgreements;
import kr.co.leteatgo.store.domain.store.value.Address;
import kr.co.leteatgo.store.domain.store.value.ChoiceLimit;
import kr.co.leteatgo.store.domain.store.value.Money;
import kr.co.leteatgo.store.domain.store.value.PositiveOrZero;
import kr.co.leteatgo.store.domain.store.value.StoreContact;
import kr.co.leteatgo.store.domain.store.value.StoreName;
import kr.co.leteatgo.store.domain.store.value.Town;

@Component
public class StoreMapper {

	public Store create(RegisterStoreRequest request, StoreLicense storeLicense,
		StoreLocation storeLocation) {
		return Store.builder()
			.name(new StoreName(request.name()))
			.phoneNumber(new PhoneNumber(request.phoneNumber()))
			.contact(createStoreContact(request.contact()))
			.license(storeLicense)
			.location(storeLocation)
			.termAgreements(createStoreTermAgreements(request.termAgreements()))
			.agent(request.agent())
			.build();
	}

	public StoreDetailResponse entityToDetail(Store store) {
		List<String> subImages = store.getImages().getImages().stream()
			.map(ImagePath::getValue)
			.toList();
		List<String> images = new ArrayList<>();
		images.add(store.getImages().getMainImage().getValue());
		images.addAll(subImages);
		return StoreDetailResponse.builder()
			.storeId(store.getId())
			.storeName(store.getName().getValue())
			.phoneNumber(store.getPhoneNumber().getValue())
			.address(store.getLocation().fullAddress())
			.dayOff(store.getBusinessHours().getDayOff())
			.event(store.getEvent().getContent())
			.coordinate(new CoordinateDto(store.getLocation().getPoint().getX(),
				store.getLocation().getPoint().getY()))
			.businessHours(store.getBusinessHours().getBusinessHours().stream()
				.map(businessHour -> new BusinessHourDto(businessHour.getBeginWeek(),
					businessHour.getEndWeek(), businessHour.getOpenAt(), businessHour.getCloseAt(),
					businessHour.getBreakStartAt(), businessHour.getBreakEndAt())).toList())
			.images(images)
			.viewCount(store.getStats().getViewCount())
			.build();
	}

	private StoreContact createStoreContact(ContactDto contact) {
		return new StoreContact(
			new PhoneNumber(contact.phoneNumber()),
			StringUtils.hasText(contact.email()) ? new Email(contact.email()) : null
		);
	}

	public StoreLicense createStoreLicense(LicenseDto license, ImagePath licenseImage,
		ImagePath businessPermitImage) {
		return StoreLicense.builder()
			.licenseNumber(new LicenseNumber(license.licenseNumber()))
			.businessType(license.businessType())
			.industry(license.industry())
			.licenseImage(licenseImage)
			.businessPermitImage(businessPermitImage)
			.build();
	}

	public StoreLocation createStoreLocation(LocationDto location, TownDto town) {
		CoordinateDto coordinate = location.coordinate();
		return StoreLocation.builder()
			.town(new Town(town.code(), town.name()))
			.address(createStoreAddress(location.address()))
			.point(GeoFactory.lngLatToPoint(coordinate.lng(), coordinate.lat()))
			.build();
	}

	private Address createStoreAddress(AddressDto address) {
		return Address.builder()
			.regionAddress(address.regionAddress())
			.roadAddress(address.roadAddress())
			.locationName(address.locationName())
			.detail(address.detail())
			.build();
	}

	private StoreTermAgreements createStoreTermAgreements(TermAgreementsDto termAgreementsDto) {
		return StoreTermAgreements.builder()
			.serviceUse(termAgreementsDto.serviceUse())
			.collectPersonalInfo(termAgreementsDto.collectPersonalInfo())
			.collectPersonalInfoForEvent(termAgreementsDto.collectPersonalInfoForEvent())
			.receiveAdSms(termAgreementsDto.receiveAdSms())
			.receiveAdEmail(termAgreementsDto.receiveAdEmail())
			.receiveAdCall(termAgreementsDto.receiveAdCall())
			.build();
	}

	public ImageSubmissionResponse entityToImageSubmissionResponse(StoreImageSubmission submission) {
		return new ImageSubmissionResponse(
			submission.getStore().getId(),
			submission.getImages().stream()
				.map(ImagePath::getValue)
				.toList()
		);
	}

	public MenuSetResponse entityToMenuSetResponse(StoreMenuSet menuSet) {
		return MenuSetResponse.builder()
			.id(menuSet.getId())
			.name(menuSet.getName())
			.groups(menuSet.getGroups().stream()
				.sorted()
				.map(group -> MenuGroupResponse.builder()
					.id(group.getId())
					.name(group.getName())
					.sort(group.getSort())
					.items(group.getItems().stream()
						.sorted()
						.map(item -> MenuItemResponse.builder()
							.id(item.getId())
							.name(item.getName())
							.description(item.getDescription())
							.image(Optional.ofNullable(item.getImage())
								.map(ImagePath::getValue)
								.orElse(null))
							.price(Optional.ofNullable(item.getPrice())
								.map(Money::getValue)
								.orElse(null))
							.sort(item.getSort())
							.status(item.getStatus())
							.optionGroups(item.getItemOptionGroups().stream()
								.sorted()
								.map(itemOptionGroup -> {
									MenuOptionGroup optionGroup = itemOptionGroup.getOptionGroup();
									return MenuOptionGroupResponse.builder()
										.id(optionGroup.getId())
										.name(optionGroup.getName())
										.limit(ChoiceLimitDto.builder()
											.max(Optional.ofNullable(optionGroup.getLimit())
												.map(ChoiceLimit::getMax)
												.map(PositiveOrZero::getValue)
												.orElse(null))
											.min(Optional.ofNullable(optionGroup.getLimit())
												.map(ChoiceLimit::getMin)
												.map(PositiveOrZero::getValue)
												.orElse(null))
											.build())
										.sort(itemOptionGroup.getSort())
										.optionItems(optionGroup.getOptionItems().stream()
											.sorted()
											.map(option -> MenuOptionItemResponse.builder()
												.id(option.getId())
												.name(option.getName())
												.price(Optional.ofNullable(option.getPrice())
													.map(Money::getValue)
													.orElse(null))
												.status(option.getStatus())
												.sort(option.getSort())
												.build())
											.toList())
										.build();
								})
								.toList())
							.build()
						).toList()
					).build())
				.toList())
			.build();
	}

	public MenuGroup createMenuGroup(AddMenuGroupRequest request) {
		return MenuGroup.builder()
			.name(request.name())
			.sort(request.sort())
			.build();
	}

	public MenuItem createMenuItem(AddMenuItemRequest request, PreSignedUrl image) {
		return MenuItem.builder()
			.name(request.name())
			.description(request.description())
			.image(Optional.ofNullable(image)
				.map(PreSignedUrl::imageUrl)
				.map(ImagePath::valueOf)
				.orElse(null))
			.price(Optional.ofNullable(request.price())
				.map(Money::valueOf)
				.orElse(null))
			.sort(request.sort())
			.status(request.status())
			.build();
	}

	public MenuOptionGroup createMenuOptionGroup(AddMenuOptionGroupRequest request, Store store) {
		return MenuOptionGroup.builder()
			.name(request.name())
			.limit(new ChoiceLimit(
				Optional.ofNullable(request.minLimit())
					.map(PositiveOrZero::valueOf)
					.orElse(null),
				Optional.ofNullable(request.maxLimit())
					.map(PositiveOrZero::valueOf)
					.orElse(null)))
			.sort(request.sort())
			.store(store)
			.build();
	}

	public MenuOptionItem createMenuOptionItem(AddMenuOptionItemRequest request,
		MenuOptionGroup optionGroup) {
		return MenuOptionItem.builder()
			.name(request.name())
			.price(Optional.ofNullable(request.price())
				.map(Money::valueOf)
				.orElse(null))
			.status(request.status())
			.sort(request.sort())
			.optionGroup(optionGroup)
			.build();
	}

	public MenuItemOptionGroup createMenuItemOptionGroup(AddMenuItemOptionGroupRequest request,
		MenuItem menuItem,
		MenuOptionGroup optionGroup) {
		return MenuItemOptionGroup.builder()
			.sort(request.sort())
			.item(menuItem)
			.optionGroup(optionGroup)
			.build();
	}

	public MenuOptionGroupResponse entityToMenuOptionGroupResponse(MenuOptionGroup menuOptionGroup) {
		return MenuOptionGroupResponse.builder()
			.id(menuOptionGroup.getId())
			.name(menuOptionGroup.getName())
			.limit(ChoiceLimitDto.builder()
				.max(Optional.ofNullable(menuOptionGroup.getLimit())
					.map(ChoiceLimit::getMax)
					.map(PositiveOrZero::getValue)
					.orElse(null))
				.min(Optional.ofNullable(menuOptionGroup.getLimit())
					.map(ChoiceLimit::getMin)
					.map(PositiveOrZero::getValue)
					.orElse(null))
				.build())
			.sort(menuOptionGroup.getSort())
			.optionItems(menuOptionGroup.getOptionItems().stream()
				.sorted()
				.map(option -> MenuOptionItemResponse.builder()
					.id(option.getId())
					.name(option.getName())
					.price(Optional.ofNullable(option.getPrice())
						.map(Money::getValue)
						.orElse(null))
					.status(option.getStatus())
					.sort(option.getSort())
					.build())
				.toList())
			.build();
	}
}
