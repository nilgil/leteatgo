package kr.co.leteatgo.store.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.leteatgo.store.domain.store.entity.MenuItem;

public interface StoreMenuItemRepository extends JpaRepository<MenuItem, Long> {

}
