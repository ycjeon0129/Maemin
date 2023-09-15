package com.tft.userservice.jwt.exception;

import com.tft.userservice.jwt.dto.Result;

public class RefreshTokenNotValidException extends RuntimeException {

    private Result result;

    public RefreshTokenNotValidException(String message) {
        this.result = Result.createErrorResult(message);
    }
}
