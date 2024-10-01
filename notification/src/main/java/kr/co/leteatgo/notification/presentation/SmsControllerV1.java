package kr.co.leteatgo.notification.presentation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.notification.application.sms.SmsService;
import kr.co.leteatgo.notification.application.sms.dto.SendSmsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Sms", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/sms")
@RestController
public class SmsControllerV1 {

  private final SmsService smsService;

  @Operation(summary = "SMS 발송")
  @PostMapping
  public void sendSms(@Valid @RequestBody SendSmsRequest request) {
    smsService.sendSms(request);
  }
}
