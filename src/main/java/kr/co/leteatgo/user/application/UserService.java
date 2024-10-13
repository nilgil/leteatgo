package kr.co.leteatgo.user.application;

import java.util.UUID;

import kr.co.leteatgo.common.aws.s3.PreSignedUrl;
import kr.co.leteatgo.user.application.dto.ChangeEmailRequest;
import kr.co.leteatgo.user.application.dto.ChangePushAgreementRequest;
import kr.co.leteatgo.user.application.dto.RegisterUserRequest;
import kr.co.leteatgo.user.domain.User;
import kr.co.leteatgo.user.presentation.dto.user.ChangeNicknameRequest;
import kr.co.leteatgo.user.presentation.dto.user.UserDetail;
import kr.co.leteatgo.user.presentation.dto.user.UserSimple;

public interface UserService {

	User getUserById(UUID userId);

	UserSimple getUserSimple(UUID userId);

	UserDetail getUserDetail(UUID userId);

	void validNickname(String nickname);

	UUID registerUser(RegisterUserRequest reqDto);

	void changeNickname(UUID userId, ChangeNicknameRequest nickname);

	void changePushAgreement(UUID userId, ChangePushAgreementRequest request);

	void changeEmail(UUID userId, ChangeEmailRequest request);

	PreSignedUrl changeProfileImage(UUID userId);

	void quitUser(UUID userId);
}
