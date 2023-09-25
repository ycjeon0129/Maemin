package com.tft.storeservice.storeImage.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tft.storeservice.storeImage.db.entity.StoreImage;

public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
}
