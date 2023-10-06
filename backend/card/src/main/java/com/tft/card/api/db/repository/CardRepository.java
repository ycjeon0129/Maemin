package com.tft.card.api.db.repository;

import com.tft.card.api.db.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByBillingKey(String billingKey);

}
