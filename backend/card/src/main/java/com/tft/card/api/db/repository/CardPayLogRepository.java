package com.tft.card.api.db.repository;

import com.tft.card.api.db.entity.CardPayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardPayLogRepository extends JpaRepository<CardPayLog, Long> {

    Optional<CardPayLog> findByPaymentKey(String PaymentKey);

}
