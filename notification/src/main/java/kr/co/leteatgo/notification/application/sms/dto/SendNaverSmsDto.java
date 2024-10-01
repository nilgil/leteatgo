package kr.co.leteatgo.notification.application.sms.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendNaverSmsDto {

  public record Request(
      String type,
      String from,
      List<MessageDto> messages,
      String content
  ) {

  }

  public record Response(
      String requestId,
      LocalDateTime requestTime,
      String statusCode,
      String statusName
  ) {

  }
}

