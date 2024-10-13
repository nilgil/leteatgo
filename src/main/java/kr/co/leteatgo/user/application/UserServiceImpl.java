package kr.co.leteatgo.user.application;

import static kr.co.leteatgo.common.aws.s3.AwsS3Folder.*;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.auth.application.user.UserAccountService;
import kr.co.leteatgo.auth.application.user.dto.RegisterUserAccountRequest;
import kr.co.leteatgo.common.aws.s3.PreSignedUrl;
import kr.co.leteatgo.common.aws.s3.PreSignedUrls;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.etc.application.storage.AwsS3Service;
import kr.co.leteatgo.user.application.dto.ChangeEmailRequest;
import kr.co.leteatgo.user.application.dto.ChangePushAgreementRequest;
import kr.co.leteatgo.user.application.dto.RegisterUserRequest;
import kr.co.leteatgo.user.application.mapper.UserMapper;
import kr.co.leteatgo.user.domain.Email;
import kr.co.leteatgo.user.domain.Nickname;
import kr.co.leteatgo.user.domain.PhoneNumber;
import kr.co.leteatgo.user.domain.ProfileImage;
import kr.co.leteatgo.user.domain.PushAgreement;
import kr.co.leteatgo.user.domain.User;
import kr.co.leteatgo.user.domain.UserRepository;
import kr.co.leteatgo.user.presentation.dto.user.ChangeNicknameRequest;
import kr.co.leteatgo.user.presentation.dto.user.UserDetail;
import kr.co.leteatgo.user.presentation.dto.user.UserSimple;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final AwsS3Service awsS3Service;
	private final UserAccountService userAccountService;

	@Override
	public User getUserById(UUID id) {
		return userRepository.findById(id).orElseThrow(
			() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE, "not found user"));
	}

	@Override
	public UserSimple getUserSimple(UUID userId) {
		User user = getUserById(userId);
		return UserSimple.from(user);
	}

	@Override
	public UserDetail getUserDetail(UUID userId) {
		User user = getUserById(userId);
		return UserDetail.from(user);
	}

	@Override
	public void validNickname(String value) {
		Nickname nickname = new Nickname(value);

		userRepository.findByNickname(nickname).ifPresent(user -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated nickname");
		});
	}

	@Transactional
	@Override
	public UUID registerUser(RegisterUserRequest request) {
		validDuplicatedPhoneNumber(request.phoneNumber());
		validDuplicatedNickname(request.nickname());

		User user = userMapper.create(request);
		user = userRepository.save(user);

		// request to auth server for register user account
		RegisterUserAccountRequest registerUserAccountRequest = new RegisterUserAccountRequest(user.getId(),
			request.phoneNumber());
		userAccountService.registerUserAccount(registerUserAccountRequest);

		return user.getId();
	}

	@Transactional
	@Override
	public void changeNickname(UUID userId, ChangeNicknameRequest request) {
		validDuplicatedNickname(request.nickname());

		Nickname nickname = new Nickname(request.nickname());

		User user = getUserById(userId);
		user.changeNickname(nickname);
	}

	@Transactional
	@Override
	public void changePushAgreement(UUID userId, ChangePushAgreementRequest request) {
		PushAgreement pushAgreement = new PushAgreement(request.pushAgreement());

		User user = getUserById(userId);
		user.changePushAgreement(pushAgreement);
	}

	@Transactional
	@Override
	public void changeEmail(UUID userId, ChangeEmailRequest request) {
		validDuplicatedEmail(request.email());

		Email email = new Email(request.email());

		User user = getUserById(userId);
		user.changeEmail(email);
	}

	@Transactional
	@Override
	public PreSignedUrl changeProfileImage(UUID userId) {
		User user = getUserById(userId);

		PreSignedUrl preSignedUrl = createPreSignedUrl().labeling("profileImage");
		ProfileImage profileImage = ProfileImage.valueOf(preSignedUrl.imageUrl());

		user.changeProfileImage(profileImage);
		return preSignedUrl;
	}

	@Transactional
	@Override
	public void quitUser(UUID userId) {
		User user = getUserById(userId);
		userRepository.delete(user);
		userAccountService.deleteUserAccount(userId);
	}

	private PreSignedUrl createPreSignedUrl() {
		PreSignedUrls preSignedUrls = awsS3Service.createPreSignedUrls(USER_PROFILE_IMAGES, 1);
		return preSignedUrls.get(0);
	}

	private void validDuplicatedPhoneNumber(String phoneNumber) {
		userRepository.findByPhoneNumber(new PhoneNumber(phoneNumber)).ifPresent(user -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated phone number");
		});
	}

	private void validDuplicatedNickname(String nickname) {
		userRepository.findByNickname(new Nickname(nickname)).ifPresent(user -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated nickname");
		});
	}

	private void validDuplicatedEmail(String email) {
		userRepository.findByEmail(new Email(email)).ifPresent(user -> {
			throw new LegException(ErrorCode.DUPLICATED_RESOURCE, "duplicated email");
		});
	}
}
