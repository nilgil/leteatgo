package kr.co.leteatgo.auth.domain.authcode;


import java.util.Optional;
import kr.co.leteatgo.auth.domain.credential.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthCodeRepository extends JpaRepository<SmsAuthCode, Long> {

  @Query("SELECT sac FROM SmsAuthCode sac " +
      "WHERE sac.phoneNumber = :phoneNumber " +
      "AND sac.authCode = :authCode " +
      "ORDER BY sac.createdAt DESC LIMIT 1")
  Optional<SmsAuthCode> getMatchedSmsAuthCode(PhoneNumber phoneNumber, AuthCode authCode);

  void deleteByPhoneNumberAndAuthCode(PhoneNumber phoneNumber, AuthCode authCode);

  void deleteByPhoneNumber(PhoneNumber phoneNumber);
}
