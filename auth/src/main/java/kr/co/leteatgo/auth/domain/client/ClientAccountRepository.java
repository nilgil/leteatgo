package kr.co.leteatgo.auth.domain.client;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, UUID> {

  Optional<ClientAccount> findByName(String name);
}
