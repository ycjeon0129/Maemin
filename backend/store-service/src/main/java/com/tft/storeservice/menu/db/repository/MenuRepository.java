package com.tft.storeservice.menu.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tft.storeservice.menu.db.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
