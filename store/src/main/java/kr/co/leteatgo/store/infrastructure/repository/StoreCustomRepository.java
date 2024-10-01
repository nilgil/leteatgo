package kr.co.leteatgo.store.infrastructure.repository;

import java.util.UUID;
import kr.co.leteatgo.store.application.dto.SearchStoresCriteria;
import kr.co.leteatgo.store.application.dto.StoreSimpleResponse;
import kr.co.leteatgo.store.domain.common.Cursor;

public interface StoreCustomRepository {

  Cursor<StoreSimpleResponse, UUID> searchStores(SearchStoresCriteria criteria);
}
