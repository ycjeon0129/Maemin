package com.tft.payservice.api.pay.db.repository;

import com.tft.payservice.api.pay.db.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

    Optional<Pay> findByPayId(Long payId);

}
