package kr.co.leteatgo.auth.domain.store;


import java.util.Optional;
import java.util.UUID;
import kr.co.leteatgo.auth.domain.credential.LoginId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreAccountRepository extends JpaRepository<StoreAccount, UUID> {

  Optional<StoreAccount> findByLoginId(LoginId loginId);
}
