package com.tft.paymentservice.common.dto;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthenticationCodeRepository extends CrudRepository<AuthenticationCode, String> {

    Optional<AuthenticationCode> findByCode(String code);
    Optional<AuthenticationCode> findByUserId(Long userId);

}
