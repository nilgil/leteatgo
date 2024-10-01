package kr.co.leteatgo.notification.infrastructure;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import common.exception.ErrorCode;
import common.exception.LegException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseInitializer {

  public static final String FIREBASE_ADMIN_KEY_PATH = "/opt/credentials/firebase-admin-key.json";
  public static final String FIREBASE_APP_NAME = "leteatgo";

  @Bean
  public FirebaseApp firebaseApp() {
    try (InputStream inputStream = new FileInputStream(FIREBASE_ADMIN_KEY_PATH)) {
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(inputStream))
          .build();
      return FirebaseApp.initializeApp(options, FIREBASE_APP_NAME);
    } catch (IOException e) {
      throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR,
          "cannot find firebase-admin-key.json file");
    }
  }

  @Bean
  public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
    return FirebaseMessaging.getInstance(firebaseApp);
  }
}
