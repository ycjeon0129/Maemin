package com.tft.userservice.sms.db.repository;

import com.tft.userservice.jwt.redis.RefreshToken;
import com.tft.userservice.sms.db.redis.SmsCheck;
import org.springframework.data.repository.CrudRepository;

public interface SmsCheckRedisRepository extends CrudRepository<SmsCheck, String> {
}
