package com.tft.paymentservice.api.payment.db.repository;

import com.tft.paymentservice.api.payment.db.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    Optional<Payment> findByPaymentId(Long paymentId);

}
