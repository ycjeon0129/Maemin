package com.tft.payservice.common.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash(value = "authentication_code")
public class AuthenticationCode {

    @Id
    private String code;

    @Indexed
    private Long userId;

    @TimeToLive
    private long ttl;

    public AuthenticationCode update(String code, long ttl) {
        this.code = code;
        this.ttl = ttl;
        return this;
    }

}
