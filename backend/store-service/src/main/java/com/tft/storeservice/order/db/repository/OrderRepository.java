package com.tft.storeservice.order.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tft.storeservice.order.db.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
