package kr.co.leteatgo.store.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.leteatgo.store.domain.store.entity.StoreMenuSet;

public interface StoreMenuSetRepository extends JpaRepository<StoreMenuSet, Long> {

}
