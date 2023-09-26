package com.tft.storeservice.store.db.repository;

import com.tft.storeservice.store.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreByStoreId(Long storeId);
}
