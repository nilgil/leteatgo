package kr.co.leteatgo.user.application.mapper;

import org.springframework.stereotype.Component;

import kr.co.leteatgo.user.application.dto.LocationDto;
import kr.co.leteatgo.user.application.dto.RegisterUserRequest;
import kr.co.leteatgo.user.application.dto.TermAgreementsDto;
import kr.co.leteatgo.user.domain.Location;
import kr.co.leteatgo.user.domain.MarkType;
import kr.co.leteatgo.user.domain.Nickname;
import kr.co.leteatgo.user.domain.PhoneNumber;
import kr.co.leteatgo.user.domain.PushAgreement;
import kr.co.leteatgo.user.domain.TermAgreements;
import kr.co.leteatgo.user.domain.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

	private final LocationMapper locationMapper;

	private static TermAgreements createTermAgreements(RegisterUserRequest request, User user) {
		TermAgreementsDto termAgreementsDto = request.termAgreements();
		return TermAgreements.builder()
			.serviceUse(termAgreementsDto.serviceUse())
			.personalCollect(termAgreementsDto.personalCollect())
			.locationBasedServiceUse(termAgreementsDto.locationBasedServiceUse())
			.electronicFinanceUse(termAgreementsDto.electronicFinanceUse())
			.personalCollectForMarketing(termAgreementsDto.personalCollectForMarketing())
			.user(user)
			.build();
	}

	public User create(RegisterUserRequest request) {
		User user = User.builder()
			.phoneNumber(new PhoneNumber(request.phoneNumber()))
			.nickname(new Nickname(request.nickname()))
			.pushAgreement(PushAgreement.init())
			.build();

		Location location = createLocation(request, user);
		user.addLocation(location);

		TermAgreements termAgreements = createTermAgreements(request, user);
		user.updateTermAgreements(termAgreements);

		return user;
	}

	private Location createLocation(RegisterUserRequest request, User user) {
		LocationDto locationDto = LocationDto.builder()
			.alias(MarkType.HOME.name())
			.marked(true)
			.address(request.location().address())
			.coordinate(request.location().coordinate())
			.build();
		return locationMapper.create(locationDto, user);
	}
}
