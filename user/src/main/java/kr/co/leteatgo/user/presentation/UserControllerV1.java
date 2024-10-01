package kr.co.leteatgo.user.presentation;


import common.aws.s3.PreSignedUrl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import kr.co.leteatgo.user.application.UserLocationService;
import kr.co.leteatgo.user.application.UserService;
import kr.co.leteatgo.user.application.dto.ChangeEmailRequest;
import kr.co.leteatgo.user.application.dto.ChangePushAgreementRequest;
import kr.co.leteatgo.user.application.dto.LocationDto;
import kr.co.leteatgo.user.application.dto.RegisterUserRequest;
import kr.co.leteatgo.user.application.dto.UserLocationResponse;
import kr.co.leteatgo.user.presentation.dto.user.ChangeNicknameRequest;
import kr.co.leteatgo.user.presentation.dto.user.UserDetail;
import kr.co.leteatgo.user.presentation.dto.user.UserSimple;
import lombok.RequiredArgsConstructor;
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

@Tag(name = "User", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@RestController
public class UserControllerV1 {

  private final UserService userService;
  private final UserLocationService userLocationService;

  @Operation(summary = "유저 간략 조회")
  @PreAuthorize("hasRole('USER')")
  @GetMapping("/me/simple")
  public UserSimple getUserSimple(@AuthenticationPrincipal UUID userId) {
    return userService.getUserSimple(userId);
  }

  @Operation(summary = "유저 상세 조회")
  @PreAuthorize("hasRole('USER')")
  @GetMapping("/me/detail")
  public UserDetail getUserDetail(@AuthenticationPrincipal UUID userId) {
    return userService.getUserDetail(userId);
  }

  @Operation(summary = "닉네임 검증")
  @GetMapping("/nickname/valid")
  public void validNickname(String nickname) {
    userService.validNickname(nickname);
  }

  @Operation(summary = "유저 등록")
  @PostMapping
  public UUID registerUser(@Valid @RequestBody RegisterUserRequest request) {
    return userService.registerUser(request);
  }

  @Operation(summary = "닉네임 변경")
  @PreAuthorize("hasRole('USER')")
  @PatchMapping("/me/nickname")
  public void changeNickname(
      @AuthenticationPrincipal UUID userId,
      @Valid @RequestBody ChangeNicknameRequest request
  ) {
    userService.changeNickname(userId, request);
  }

  @Operation(summary = "이메일 변경")
  @PreAuthorize("hasRole('USER')")
  @PatchMapping("/me/email")
  public void changeEmail(
      @AuthenticationPrincipal UUID userId,
      @Valid @RequestBody ChangeEmailRequest request
  ) {
    userService.changeEmail(userId, request);
  }

  @Operation(summary = "프로필 사진 변경")
  @PreAuthorize("hasRole('USER')")
  @PatchMapping("/me/profile-image")
  public PreSignedUrl changeProfileImage(@AuthenticationPrincipal UUID userId) {
    return userService.changeProfileImage(userId);
  }

  @Operation(summary = "푸시 허용 설정 변경")
  @PreAuthorize("hasRole('USER')")
  @PatchMapping("/me/push-agreement")
  public void changePushAgreement(
      @AuthenticationPrincipal UUID userId,
      @Valid @RequestBody ChangePushAgreementRequest request
  ) {
    userService.changePushAgreement(userId, request);
  }

  @Operation(summary = "유저 탈퇴")
  @PreAuthorize("hasRole('USER')")
  @DeleteMapping("/me")
  public void quitUser(@AuthenticationPrincipal UUID userId) {
    userService.quitUser(userId);
  }

  @Operation(summary = "유저 위치 목록 조회")
  @PreAuthorize("hasRole('USER')")
  @GetMapping("/me/locations")
  public List<UserLocationResponse> getUserLocations(@AuthenticationPrincipal UUID userId) {
    return userLocationService.getUserLocations(userId);
  }

  @Operation(summary = "유저 활성 위치 조회")
  @PreAuthorize("hasRole('USER')")
  @GetMapping("/me/locations/activated")
  public UserLocationResponse getActivatedUserLocation(@AuthenticationPrincipal UUID userId) {
    return userLocationService.getActivatedUserLocation(userId);
  }

  @Operation(summary = "유저 위치 추가")
  @PreAuthorize("hasRole('USER')")
  @PostMapping("/me/locations")
  public void addUserLocation(
      @AuthenticationPrincipal UUID userId,
      @Valid @RequestBody LocationDto request
  ) {
    userLocationService.addUserLocation(userId, request);
  }

  @Operation(summary = "유저 위치 활성화")
  @PreAuthorize("hasRole('USER')")
  @PatchMapping("/me/locations/{locationId}/active")
  public void activeUserLocation(
      @AuthenticationPrincipal UUID userId,
      @PathVariable Long locationId
  ) {
    userLocationService.activeUserLocation(userId, locationId);
  }

  @Operation(summary = "유저 위치 삭제")
  @PreAuthorize("hasRole('USER')")
  @DeleteMapping("/me/locations/{locationId}")
  public void deleteUserLocation(
      @AuthenticationPrincipal UUID userId,
      @PathVariable Long locationId
  ) {
    userLocationService.deleteUserLocation(userId, locationId);
  }
}
