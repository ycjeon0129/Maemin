package com.tft.storeservice.order.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tft.storeservice.order.db.entity.OrderMenus;

public interface OrderMenusRepository extends JpaRepository<OrderMenus, Long> {
}
