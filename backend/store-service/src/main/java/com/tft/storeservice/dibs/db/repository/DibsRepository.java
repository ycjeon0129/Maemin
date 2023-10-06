package com.tft.storeservice.dibs.db.repository;

import com.tft.storeservice.dibs.db.entity.Dibs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DibsRepository extends JpaRepository<Dibs, Long> {
}
