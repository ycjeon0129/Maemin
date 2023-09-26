package com.tft.storeservice.menu.db.repository;

import java.util.List;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.store.db.entity.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import feign.Param;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	Menu findMenuByMenuId(Long menuId);
	@Query("SELECT m FROM Menu m WHERE m.store.storeId = :storeId")
	List<Menu> findAllByStoreId(@Param("storeId") Long storeId);

	List<Menu> findByStore_StoreId(Long storeId);
}
