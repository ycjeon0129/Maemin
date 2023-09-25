package com.tft.storeservice.store.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tft.storeservice.store.db.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
	Store findStoreByStoreId(Long storeId);
}
