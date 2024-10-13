package kr.co.leteatgo.user.presentation.dto.user;

import java.util.Optional;
import java.util.UUID;

import kr.co.leteatgo.user.domain.Email;
import kr.co.leteatgo.user.domain.ProfileImage;
import kr.co.leteatgo.user.domain.User;
import lombok.Builder;

@Builder
public record UserDetail(
	UUID id,
	String phoneNumber,
	String nickname,
	String email,
	String profileImage,
	Boolean pushAgreement
) {

	public static UserDetail from(User user) {
		String emailOpt = Optional.ofNullable(user.getEmail())
			.map(Email::getValue)
			.orElse(null);
		String profileImageOpt = Optional.ofNullable(user.getProfileImage())
			.map(ProfileImage::getValue)
			.orElse(null);
		return UserDetail.builder()
			.id(user.getId())
			.phoneNumber(user.getPhoneNumber().getValue())
			.nickname(user.getNickname().getValue())
			.pushAgreement(user.getPushAgreement().getValue())
			.email(emailOpt)
			.profileImage(profileImageOpt)
			.build();
	}
}
