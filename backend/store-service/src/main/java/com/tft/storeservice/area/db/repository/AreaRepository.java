package com.tft.storeservice.area.db.repository;

import com.tft.storeservice.area.db.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Integer> {
    Area findAreaByAreaCode(int areaCode);
    Area findByArea(String area);
}
