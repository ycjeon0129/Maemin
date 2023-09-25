package com.tft.storeservice.dibs.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tft.storeservice.dibs.db.entity.Dibs;

public interface DibsRepository extends JpaRepository<Dibs, Long> {
}
