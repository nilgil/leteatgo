package kr.co.leteatgo.notification.application.email;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class EmailServiceImplTest {

  @Autowired
  JavaMailSender javaMailSender;

  @Test
  void name() {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
  }
}