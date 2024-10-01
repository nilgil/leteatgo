package kr.co.leteatgo.auth.domain.credential;

public interface PhoneCredential extends Credential {

  PhoneNumber phoneNumber();

  DeviceToken deviceToken();

  boolean matchDeviceToken(DeviceToken deviceToken);

  void updateDeviceToken(DeviceToken deviceToken);
}
