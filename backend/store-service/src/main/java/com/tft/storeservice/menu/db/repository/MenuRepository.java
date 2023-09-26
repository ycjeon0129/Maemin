package com.tft.storeservice.menu.db.repository;

import com.tft.storeservice.menu.db.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
