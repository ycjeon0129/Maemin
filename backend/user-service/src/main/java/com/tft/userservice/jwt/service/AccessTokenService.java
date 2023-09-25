package com.tft.userservice.jwt.service;

import com.tft.userservice.common.exception.custom.AccessTokenNotValidException;
import com.tft.userservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccessTokenService {

    private final JwtTokenProvider jwtTokenProvider;

    public void checkAccessToken(String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer", "");

        if (!jwtTokenProvider.validateJwtToken(token)) {
            throw new AccessTokenNotValidException();
        }
    }
}
