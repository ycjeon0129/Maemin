package com.tft.storeservice.storeImage.db.repository;

import com.tft.storeservice.storeImage.db.entity.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
}
