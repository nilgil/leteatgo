package kr.co.leteatgo.user.application;

import java.util.List;
import java.util.UUID;

import kr.co.leteatgo.user.application.dto.LocationDto;
import kr.co.leteatgo.user.application.dto.UserLocationResponse;

public interface UserLocationService {

	List<UserLocationResponse> getUserLocations(UUID userId);

	UserLocationResponse getActivatedUserLocation(UUID userId);

	void addUserLocation(UUID userId, LocationDto request);

	void activeUserLocation(UUID userId, Long locationId);

	void deleteUserLocation(UUID userId, Long locationId);
}
