package com.tft.storeservice.menuoption.db.repository;

import com.tft.storeservice.menuoption.db.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
