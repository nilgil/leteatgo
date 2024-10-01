package kr.co.leteatgo.store.infrastructure.repository;

import java.util.List;
import kr.co.leteatgo.store.domain.store.entity.MenuOptionGroup;
import kr.co.leteatgo.store.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMenuOptionGroupRepository extends JpaRepository<MenuOptionGroup, Long> {

  List<MenuOptionGroup> findByStore(Store store);
}
