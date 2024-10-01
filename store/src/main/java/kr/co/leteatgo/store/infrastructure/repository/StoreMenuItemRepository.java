package kr.co.leteatgo.store.infrastructure.repository;

import kr.co.leteatgo.store.domain.store.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMenuItemRepository extends JpaRepository<MenuItem, Long> {

}
