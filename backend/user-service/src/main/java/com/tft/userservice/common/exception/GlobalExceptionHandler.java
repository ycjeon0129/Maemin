package com.tft.userservice.common.exception;

import com.tft.userservice.common.exception.custom.AccessTokenNotValidException;
import com.tft.userservice.common.exception.custom.GameNotExistException;
import com.tft.userservice.common.exception.custom.UsernameNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {GameNotExistException.class})
    public ResponseEntity<Object> handelGameNotExistException(GameNotExistException e) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                ExceptionMessage.GAME_NOT_EXIST_MESSAGE,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, httpStatus);

    }

    @ExceptionHandler(value = {UsernameNotExistException.class})
    public ResponseEntity<Object> handelUsernameNotExistException(UsernameNotExistException e) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                ExceptionMessage.USER_NOT_EXIST_MESSAGE,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, httpStatus);

    }

    @ExceptionHandler(value = {AccessTokenNotValidException.class})
    public ResponseEntity<Object> handelAccessTokenNotValidException(AccessTokenNotValidException e) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                ExceptionMessage.ACCESS_TOKEN_NOT_VALID,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, httpStatus);

    }


}
