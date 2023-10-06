package com.tft.payservice.api.pay.db.repository;

import com.tft.payservice.api.pay.db.entity.PayUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayUserRepository extends JpaRepository<PayUser, Long> {

    Optional<PayUser> findByUserId(Long userId);

}
