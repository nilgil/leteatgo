package kr.co.leteatgo.notification.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.notification.application.email.EmailService;
import kr.co.leteatgo.notification.application.email.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Email", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/email")
@RestController
public class EmailControllerV1 {

  private final EmailService emailService;

  @Operation(summary = "Email 발송")
  @PostMapping
  public void sendEmail(@Valid @RequestBody SendEmailRequest request) {
    emailService.sendEmails(request);
  }
}
