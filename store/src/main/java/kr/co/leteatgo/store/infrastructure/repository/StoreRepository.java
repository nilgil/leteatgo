package kr.co.leteatgo.store.infrastructure.repository;

import java.util.UUID;
import kr.co.leteatgo.store.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, UUID>, StoreCustomRepository {

}
