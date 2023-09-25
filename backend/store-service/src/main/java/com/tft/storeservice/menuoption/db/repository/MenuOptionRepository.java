package com.tft.storeservice.menuoption.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tft.storeservice.menuoption.db.entity.MenuOption;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
