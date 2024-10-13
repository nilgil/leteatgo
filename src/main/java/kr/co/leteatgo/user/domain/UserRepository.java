package kr.co.leteatgo.user.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByPhoneNumber(PhoneNumber phoneNumber);

	Optional<User> findByNickname(Nickname nickname);

	Optional<User> findByEmail(Email email);
}
