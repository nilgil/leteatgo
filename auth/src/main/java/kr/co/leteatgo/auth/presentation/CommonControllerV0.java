package kr.co.leteatgo.auth.presentation;

import static kr.co.leteatgo.auth.application.common.VersioningUtil.rewritePhoneNumber;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.co.leteatgo.auth.application.authcode.dto.MatchAuthCodeRequest;
import kr.co.leteatgo.auth.application.authcode.dto.SendAuthCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@Tag(name = "Common Auth", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/common")
@RestController
public class CommonControllerV0 {

  private final CommonControllerV1 commonControllerV1;

  @Operation(summary = "인증 코드 전송")
  @PostMapping("/auth-code")
  public Boolean sendAuthCode(@Valid @RequestBody SendAuthCodeRequestV0 request) {
    String phoneNumber = rewritePhoneNumber(request.phone());
    try {
      commonControllerV1.sendAuthCode(new SendAuthCodeRequest(phoneNumber));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public record SendAuthCodeRequestV0(@NotBlank String phone) {

  }

  @Operation(summary = "인증 코드 매칭")
  @PostMapping("/auth-code/match")
  public Boolean matchAuthCode(@Valid @RequestBody MatchAuthCodeRequestV0 request) {
    String phoneNumber = rewritePhoneNumber(request.phone());
    return commonControllerV1.matchAuthCode(
        new MatchAuthCodeRequest(phoneNumber, request.authCode()));
  }

  public record MatchAuthCodeRequestV0(@NotBlank String phone, @NotBlank String authCode) {

  }
}
