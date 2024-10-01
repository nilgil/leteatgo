package kr.co.leteatgo.store.infrastructure.repository;

import kr.co.leteatgo.store.domain.store.entity.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMenuGroupRepository extends JpaRepository<MenuGroup, Long> {

}
