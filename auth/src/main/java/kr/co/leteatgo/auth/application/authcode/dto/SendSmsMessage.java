package kr.co.leteatgo.auth.application.authcode.dto;


import java.util.List;
import kr.co.leteatgo.auth.domain.authcode.SmsAuthCode;

public record SendSmsMessage(
    List<String> phoneNumbers,
    String content
) {

  public static SendSmsMessage of(SmsAuthCode smsAuthCode, String authCodeSmsFormat) {
    List<String> phoneNumbers = List.of(smsAuthCode.phoneNumber());
    String content = String.format(authCodeSmsFormat, smsAuthCode.authCode());
    return new SendSmsMessage(phoneNumbers, content);
  }
}
