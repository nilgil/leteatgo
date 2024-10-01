package kr.co.leteatgo.auth.application.authcode;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import kr.co.leteatgo.auth.application.authcode.dto.SendSmsMessage;
import kr.co.leteatgo.auth.application.common.ApiSender;
import kr.co.leteatgo.auth.domain.authcode.AuthCode;
import kr.co.leteatgo.auth.domain.authcode.AuthCodeRepository;
import kr.co.leteatgo.auth.domain.authcode.SmsAuthCode;
import kr.co.leteatgo.auth.domain.credential.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthCodeServiceImpl implements AuthCodeService {

  private static final String AUTH_CODE_SMS_FORMAT = "Let Eat Go 인증번호 : [%s] 입니다.";

  private final AuthCodeRepository authCodeRepository;
  private final ApiSender apiSender;

  private final Map<PhoneNumber, AuthCode> testAccounts;

  public AuthCodeServiceImpl(AuthCodeRepository authCodeRepository, ApiSender apiSender,
      @Value("${test-accounts}") String testAccountsValue) {
    this.authCodeRepository = authCodeRepository;
    this.apiSender = apiSender;
    this.testAccounts = initTestAccounts(testAccountsValue);
  }

  private static Map<PhoneNumber, AuthCode> initTestAccounts(String testAccountsValue) {
    Map<PhoneNumber, AuthCode> testAccounts = new HashMap<>();
    Arrays.stream(testAccountsValue.split(",")).forEach(testAccount -> {
      String[] account = testAccount.split(":");
      PhoneNumber phoneNumber = new PhoneNumber(account[0]);
      AuthCode authCode = new AuthCode(account[1]);
      testAccounts.put(phoneNumber, authCode);
    });
    return testAccounts;
  }

  @Transactional
  @Override
  public void sendAuthCode(String phoneNumber) {
    PhoneNumber phoneNumberObj = new PhoneNumber(phoneNumber);

    if (testAccounts.containsKey(phoneNumberObj)) {
      return;
    }

    SmsAuthCode smsAuthCode = new SmsAuthCode(phoneNumberObj, new AuthCode());

    SendSmsMessage message = SendSmsMessage.of(smsAuthCode, AUTH_CODE_SMS_FORMAT);
    apiSender.NOTI$sendSms(message);

    authCodeRepository.deleteByPhoneNumber(phoneNumberObj);
    authCodeRepository.save(smsAuthCode);
  }

  @Transactional(readOnly = true)
  @Override
  public Boolean matchAuthCode(String phoneNumber, String authCode) {
    PhoneNumber phoneNumberObj = new PhoneNumber(phoneNumber);
    AuthCode authCodeObj = new AuthCode(authCode);

    if (isTestAccount(phoneNumberObj, authCodeObj)) {
      return true;
    }

    Optional<SmsAuthCode> smsAuthCodeOpt = authCodeRepository.getMatchedSmsAuthCode(
        phoneNumberObj, authCodeObj);
    if (smsAuthCodeOpt.isEmpty()) {
      return false;
    }
    SmsAuthCode smsAuthCode = smsAuthCodeOpt.get();

    LocalDateTime fiveMinAgo = LocalDateTime.now().minusMinutes(5);
    return !smsAuthCode.isCreatedBefore(fiveMinAgo);
  }

  @Override
  public void deleteAuthCode(String phoneNumber) {
    authCodeRepository.deleteByPhoneNumber(new PhoneNumber(phoneNumber));
  }

  private boolean isTestAccount(PhoneNumber phoneNumber, AuthCode authCode) {
    return Optional.ofNullable(testAccounts.get(phoneNumber))
        .map(code -> code.equals(authCode))
        .orElse(false);
  }
}
