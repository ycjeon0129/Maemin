package com.tft.storeservice.area.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tft.storeservice.area.db.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Integer> {
	Optional<Area> findAreaByAreaCode(int areaCode);
}
