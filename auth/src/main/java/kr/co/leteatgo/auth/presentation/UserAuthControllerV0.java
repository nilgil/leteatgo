package kr.co.leteatgo.auth.presentation;

import static kr.co.leteatgo.auth.application.common.VersioningUtil.rewritePhoneNumber;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import kr.co.leteatgo.auth.application.user.dto.LoginUserAccountRequest;
import kr.co.leteatgo.auth.application.user.dto.ReissueUserAccountRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Deprecated
@Tag(name = "User Auth", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserAuthControllerV0 {

  private final UserAuthControllerV1 userAuthController;
  private final WebClient userWebClient;

  @Operation(summary = "유저 계정 추가")
  @PostMapping("/join")
  public UUID joinUserAccount(@RequestBody JoinUserAccountRequestV0 requestV0) {
    JoinUserAccountRequestV1 requestV1 = JoinUserAccountRequestV1.builder().
        phoneNumber(rewritePhoneNumber(requestV0.phone()))
        .nickname(requestV0.nickname())
        .location(LocationDto.builder()
            .address(requestV0.address())
            .coordinate(new CoordinateDto(
                requestV0.address.coordinate().lng,
                requestV0.address.coordinate().lat))
            .build())
        .termAgreements(requestV0.termsAgreement())
        .build();
    return userWebClient.post().uri("/v1/users")
        .bodyValue(requestV1)
        .retrieve()
        .bodyToMono(UUID.class)
        .block();
  }

  @Operation(summary = "유저 로그인")
  @PostMapping("/login")
  public JwtSetResponse loginUserAccount(@RequestBody LoginUserAccountRequestV0 requestV0) {
    LoginUserAccountRequest requestV1 = new LoginUserAccountRequest(
        rewritePhoneNumber(requestV0.phone), requestV0.deviceToken,
        requestV0.authCode);
    return userAuthController.loginUserAccount(requestV1);
  }

  @Operation(summary = "유저 토큰 재발급")
  @PostMapping("/reissue")
  public JwtSetResponse reissueJwt(@RequestBody ReissueUserAccountRequest request) {
    return userAuthController.refreshUserAccount(request);
  }

  @Operation(summary = "유저 계정 탈퇴")
  @PreAuthorize("hasRole('USER')")
  @DeleteMapping("/quit")
  public void quitUserAccount(HttpServletRequest request) {
    userWebClient.delete().uri("/v1/users/me")
        .header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION))
        .retrieve()
        .bodyToMono(Void.class).block();
  }

  public record JoinUserAccountRequestV0(
      String phone,
      String nickname,
      AddressDto address,
      TermAgreementsDto termsAgreement
  ) {

  }

  @Builder
  public record JoinUserAccountRequestV1(
      String phoneNumber,
      String nickname,
      LocationDto location,
      TermAgreementsDto termAgreements
  ) {

  }

  @Builder
  public record LocationDto(
      AddressDto address,
      CoordinateDto coordinate
  ) {

  }

  public record AddressDto(
      String regionAddress,
      String roadAddress,
      String locationName,
      String detail,
      CoordinateDto coordinate
  ) {

  }

  public record CoordinateDto(
      Double lng,
      Double lat
  ) {

  }

  public record TermAgreementsDto(
      Boolean serviceUse,
      Boolean personalCollect,
      Boolean locationBasedServiceUse,
      Boolean electronicFinanceUse,
      Boolean personalCollectForMarketing
  ) {

  }

  public record LoginUserAccountRequestV0(
      @NotBlank String phone,
      @NotBlank String deviceToken,
      @NotBlank String authCode
  ) {

  }
}
