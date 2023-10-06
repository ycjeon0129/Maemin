package com.tft.storeservice.store.db.repository;

import java.util.List;

import com.tft.storeservice.area.db.entity.Area;
import com.tft.storeservice.store.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreByStoreId(Long storeId);
    List<Store> findAllByArea(Area area);
    List<Store> findAllBy();

}
