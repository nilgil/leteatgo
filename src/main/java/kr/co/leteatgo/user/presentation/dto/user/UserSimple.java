package kr.co.leteatgo.user.presentation.dto.user;

import java.util.Optional;
import java.util.UUID;

import kr.co.leteatgo.user.domain.ProfileImage;
import kr.co.leteatgo.user.domain.User;
import lombok.Builder;

@Builder
public record UserSimple(
	UUID id,
	String nickname,
	String profileImage
) {

	public static UserSimple from(User user) {
		String profileImageOpt = Optional.ofNullable(user.getProfileImage())
			.map(ProfileImage::getValue)
			.orElse(null);
		return UserSimple.builder()
			.id(user.getId())
			.nickname(user.getNickname().getValue())
			.profileImage(profileImageOpt)
			.build();
	}
}
