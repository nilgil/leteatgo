package kr.co.leteatgo.store.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.leteatgo.store.domain.store.StoreImageSubmission;
import kr.co.leteatgo.store.domain.store.entity.Store;

public interface StoreImageSubmissionRepository extends
	JpaRepository<StoreImageSubmission, Long> {

	Optional<StoreImageSubmission> findByStore(Store store);
}
