package kr.co.leteatgo.store.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.leteatgo.store.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, UUID>, StoreCustomRepository {

}
