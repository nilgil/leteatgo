package kr.co.leteatgo.store.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.leteatgo.store.domain.store.entity.MenuGroup;

public interface StoreMenuGroupRepository extends JpaRepository<MenuGroup, Long> {

}
