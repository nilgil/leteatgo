package kr.co.leteatgo.notification.application.sms;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import kr.co.leteatgo.notification.application.sms.dto.SendSmsRequest;

@Profile("local")
@Component
public class NoOpSmsService implements SmsService {

	@Override
	public void sendSms(SendSmsRequest request) {
	}
}
