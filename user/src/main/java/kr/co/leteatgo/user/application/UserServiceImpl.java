package kr.co.leteatgo.user.application;


import static common.aws.s3.AwsS3Folder.USER_PROFILE_IMAGES;

import common.aws.s3.PreSignedUrl;
import common.aws.s3.PreSignedUrlRequest;
import common.aws.s3.PreSignedUrls;
import common.exception.ErrorCode;
import common.exception.LegException;
import java.util.UUID;
import kr.co.leteatgo.user.application.dto.ChangeEmailRequest;
import kr.co.leteatgo.user.application.dto.ChangePushAgreementRequest;
import kr.co.leteatgo.user.application.dto.RegisterUserAccountMsg;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ApiSender apiSender;
  private final UserMapper userMapper;

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
    RegisterUserAccountMsg msg = new RegisterUserAccountMsg(user.getId(), request.phoneNumber());
    apiSender.AUTH$registerUserAccount(msg);

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

    apiSender.AUTH$deleteUserAccount();
  }

  private PreSignedUrl createPreSignedUrl() {
    PreSignedUrlRequest request = new PreSignedUrlRequest(USER_PROFILE_IMAGES, 1);

    PreSignedUrls preSignedUrls = apiSender.API$createPreSignedUrls(request);

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
