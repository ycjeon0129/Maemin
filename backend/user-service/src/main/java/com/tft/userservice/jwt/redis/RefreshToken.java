package com.tft.userservice.jwt.redis;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    private Long userId;
    private String refreshTokenId;

    public static RefreshToken of(Long userId, String refreshTokenId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.userId = userId;
        refreshToken.refreshTokenId = refreshTokenId;
        return refreshToken;
    }
}
