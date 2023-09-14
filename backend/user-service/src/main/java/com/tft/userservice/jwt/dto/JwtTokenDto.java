package com.tft.userservice.jwt.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class JwtTokenDto {
    private final String accessToken;
    private final Date accessTokenExpiredDate;
    private final String refreshToken;

    @Builder
    public JwtTokenDto(String accessToken, String refreshToken, Date accessTokenExpiredDate) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiredDate = accessTokenExpiredDate;
    }
}
