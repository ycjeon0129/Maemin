package com.tft.userservice.jwt.repository;

import com.tft.userservice.jwt.redis.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, Long> {
}
