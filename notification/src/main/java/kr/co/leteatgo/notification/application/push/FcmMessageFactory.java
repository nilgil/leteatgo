package kr.co.leteatgo.notification.application.push;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import java.util.List;
import java.util.Map;
import kr.co.leteatgo.notification.application.push.dto.PushInfo;

public class FcmMessageFactory {

  private static final ApnsConfig DEFAULT_APNS_CONFIG = ApnsConfig.builder()
      .setAps(Aps.builder().setContentAvailable(true).build()).build();
  private static final AndroidConfig DEFAULT_ANDROID_CONFIG = AndroidConfig.builder()
      .setPriority(AndroidConfig.Priority.HIGH).build();

  public static Notification createNotification(PushInfo pushInfo) {
    return Notification.builder()
        .setTitle(pushInfo.title())
        .setBody(pushInfo.body())
        .setImage(pushInfo.imageUrl())
        .build();
  }

  public static Message createMessage(String deviceToken, Notification notification,
      Map<String, String> data) {

    return Message.builder()
        .setToken(deviceToken)
        .putAllData(data)
        .setNotification(notification)
        .setApnsConfig(DEFAULT_APNS_CONFIG)
        .setAndroidConfig(DEFAULT_ANDROID_CONFIG)
        .build();
  }

  public static MulticastMessage createMulticastMessage(List<String> deviceTokens,
      Notification notification, Map<String, String> data) {
    return MulticastMessage.builder()
        .addAllTokens(deviceTokens)
        .putAllData(data)
        .setNotification(notification)
        .setApnsConfig(DEFAULT_APNS_CONFIG)
        .setAndroidConfig(DEFAULT_ANDROID_CONFIG)
        .build();
  }
}
