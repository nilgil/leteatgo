package kr.co.leteatgo.user.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.user.application.dto.LocationDto;
import kr.co.leteatgo.user.application.dto.UserLocationResponse;
import kr.co.leteatgo.user.application.mapper.LocationMapper;
import kr.co.leteatgo.user.domain.Location;
import kr.co.leteatgo.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserLocationServiceImpl implements UserLocationService {

	private final UserService userService;
	private final LocationMapper locationMapper;

	@Override
	public List<UserLocationResponse> getUserLocations(UUID userId) {
		User user = userService.getUserById(userId);
		return user.getLocations().stream()
			.sorted((ul1, ul2) -> ul2.getCreatedAt().compareTo(ul1.getCreatedAt()))
			.map(UserLocationResponse::from)
			.toList();
	}

	@Override
	public UserLocationResponse getActivatedUserLocation(UUID userId) {
		User user = userService.getUserById(userId);
		return user.getLocations().getLocations().stream()
			.filter(Location::getActive)
			.findFirst()
			.map(UserLocationResponse::from)
			.orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE,
				"cannot found activated user location"));
	}

	@Transactional
	@Override
	public void addUserLocation(UUID userId, LocationDto request) {
		User user = userService.getUserById(userId);

		Location location = locationMapper.create(request, user);
		user.addLocation(location);
	}

	@Transactional
	@Override
	public void activeUserLocation(UUID userId, Long locationId) {
		User user = userService.getUserById(userId);

		user.getLocations().stream()
			.forEach(ul -> ul.activeLocation(false));

		user.getLocations().stream()
			.filter(ul -> ul.getId().equals(locationId))
			.findFirst()
			.orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE,
				"cannot found user location"))
			.activeLocation(true);
	}

	@Transactional
	@Override
	public void deleteUserLocation(UUID userId, Long locationId) {
		User user = userService.getUserById(userId);

		Location location = user.getLocations().stream()
			.filter(ul -> ul.getId().equals(locationId))
			.findFirst()
			.orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE,
				"cannot found user location"));
		user.removeLocation(location);

		//    syncUserLocations(user);
	}

	//  private void syncUserLocations(User user) {
	//    List<AddUserLocationMessage> addUserLocationMessages = user.getLocations().stream()
	//        .map(AddUserLocationMessage::from)
	//        .toList();
	//    ResetUserLocationsMessage message = ResetUserLocationsMessage.builder()
	//        .userId(user.getId())
	//        .locations(addUserLocationMessages)
	//        .build();
	//    apiSender.GEO$resetUserLocations(message);
	//  }
}
