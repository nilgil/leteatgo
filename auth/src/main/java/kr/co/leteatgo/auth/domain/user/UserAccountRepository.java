package kr.co.leteatgo.auth.domain.user;

import java.util.Optional;
import java.util.UUID;
import kr.co.leteatgo.auth.domain.credential.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

  Optional<UserAccount> findByPhoneNumber(PhoneNumber phoneNumber);
}
