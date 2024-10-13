package kr.co.leteatgo.user.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.user.application.dto.AddressDto;
import kr.co.leteatgo.user.application.dto.AddressDtoV0;
import kr.co.leteatgo.user.application.dto.ChangePushAgreementRequest;
import kr.co.leteatgo.user.application.dto.LocationDto;
import kr.co.leteatgo.user.application.dto.UserLocationResponse;
import kr.co.leteatgo.user.application.dto.UserLocationResponseV0;
import kr.co.leteatgo.user.presentation.dto.user.ChangeNicknameRequest;
import kr.co.leteatgo.user.presentation.dto.user.UserDetail;
import kr.co.leteatgo.user.presentation.dto.user.UserSimple;
import lombok.RequiredArgsConstructor;

@Deprecated
@Tag(name = "User", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserControllerV0 {

	private final UserControllerV1 userController;

	@Operation(summary = "유저 간략 조회")
	@GetMapping("/me/simple")
	public UserSimpleV0 getUserSimple(@AuthenticationPrincipal UUID userId) {
		UserSimple response = userController.getUserSimple(userId);
		return new UserSimpleV0(response.id(), response.nickname(), response.profileImage());
	}

	@Operation(summary = "유저 상세 조회")
	@GetMapping("/me/detail")
	public UserDetailV0 getUserDetail(@AuthenticationPrincipal UUID userId) {
		UserDetail response = userController.getUserDetail(userId);
		return new UserDetailV0(response.id(), response.phoneNumber(), response.nickname(),
			response.email(), response.profileImage(), response.pushAgreement());
	}

	@Operation(summary = "닉네임 중복 체크")
	@GetMapping("/nicknames/exist")
	public Boolean validDuplicatedNickname(String nickname) {
		try {
			userController.validNickname(nickname);
			return false;
		} catch (LegException e) {
			if (e.errorCode() == ErrorCode.DUPLICATED_RESOURCE) {
				return true;
			}
			throw e;
		}
	}

	@Operation(summary = "유저 닉네임 변경")
	@PatchMapping("/me/nickname")
	public void changeNickname(
		@AuthenticationPrincipal UUID userId,
		@RequestBody ChangeNicknameRequest request
	) {
		userController.changeNickname(userId, request);
	}

	@Operation(summary = "유저 Push 활성화")
	@PatchMapping("/me/notify/active")
	public Boolean activeNotify(@AuthenticationPrincipal UUID userId) {
		userController.changePushAgreement(userId, new ChangePushAgreementRequest(true));
		return true;
	}

	@Operation(summary = "유저 Push 비활성화")
	@PatchMapping("/me/notify/inactive")
	public Boolean inactiveNotify(@AuthenticationPrincipal UUID userId) {
		userController.changePushAgreement(userId, new ChangePushAgreementRequest(false));
		return true;
	}

	@Operation(summary = "유저 위치 목록 조회")
	@GetMapping("/me/locations")
	public List<UserLocationResponseV0> getUserLocations(@AuthenticationPrincipal UUID userId) {
		return userController.getUserLocations(userId).stream()
			.map(userLocationResponse -> UserLocationResponseV0.builder()
				.id(userLocationResponse.id())
				.alias(userLocationResponse.alias())
				.marked(userLocationResponse.marked())
				.active(userLocationResponse.active())
				.address(AddressDtoV0.builder()
					.regionAddress(userLocationResponse.address().regionAddress())
					.roadAddress(userLocationResponse.address().roadAddress())
					.locationName(userLocationResponse.address().locationName())
					.detail(userLocationResponse.address().detail())
					.coordinate(userLocationResponse.coordinate())
					.build())
				.build())
			.toList();
	}

	@Operation(summary = "유저 활성 위치 조회")
	@GetMapping("/me/locations/activated")
	public UserLocationResponseV0 getActivatedUserLocation(@AuthenticationPrincipal UUID userId) {
		UserLocationResponse userLocationResponse = userController.getActivatedUserLocation(userId);
		return UserLocationResponseV0.builder()
			.id(userLocationResponse.id())
			.alias(userLocationResponse.alias())
			.marked(userLocationResponse.marked())
			.active(userLocationResponse.active())
			.address(AddressDtoV0.builder()
				.regionAddress(userLocationResponse.address().regionAddress())
				.roadAddress(userLocationResponse.address().roadAddress())
				.locationName(userLocationResponse.address().locationName())
				.detail(userLocationResponse.address().detail())
				.coordinate(userLocationResponse.coordinate())
				.build())
			.build();
	}

	@Operation(summary = "유저 위치 추가")
	@PostMapping("/me/locations")
	public void addUserLocation(
		@AuthenticationPrincipal UUID userId,
		@RequestBody LocationDtoV0 request
	) {
		LocationDto converted = LocationDto.builder()
			.alias(request.alias())
			.marked(request.marked())
			.address(AddressDto.builder()
				.regionAddress(request.address().regionAddress())
				.roadAddress(request.address().roadAddress())
				.locationName(request.address().locationName())
				.detail(request.address().detail())
				.build())
			.coordinate(request.address().coordinate())
			.build();
		userController.addUserLocation(userId, converted);
	}

	@Operation(summary = "유저 위치 활성화")
	@PatchMapping("/me/locations/{locationId}/active")
	public void activeUserLocation(
		@AuthenticationPrincipal UUID userId,
		@PathVariable Long locationId
	) {
		userController.activeUserLocation(userId, locationId);
	}

	@Operation(summary = "유저 위치 삭제")
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/me/locations/{locationId}")
	public void deleteUserLocation(
		@AuthenticationPrincipal UUID userId,
		@PathVariable Long locationId
	) {
		userController.deleteUserLocation(userId, locationId);
	}

	public record UserSimpleV0(UUID id, String nickname, String profile) {

	}

	public record UserDetailV0(UUID id, String phone, String nickname, String email,
							   String profile, @JsonProperty("notify") Boolean pushAgreement) {

	}

	public record LocationDtoV0(
		String alias,
		Boolean marked,
		AddressDtoV0 address
	) {

	}
}
