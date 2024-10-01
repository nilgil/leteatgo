package kr.co.leteatgo.notification.application.sms;


import kr.co.leteatgo.notification.application.sms.dto.SendSmsRequest;

public interface SmsService {

  void sendSms(SendSmsRequest request);
}
