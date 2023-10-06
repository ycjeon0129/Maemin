package com.tft.userservice.common.exception;

import com.tft.userservice.common.exception.custom.AccessTokenNotValidException;
import com.tft.userservice.common.exception.custom.LoginIdExistException;
import com.tft.userservice.common.exception.custom.SmsNumNotValidException;
import com.tft.userservice.common.exception.custom.UserNotExistException;
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
    @ExceptionHandler(value = {UserNotExistException.class})
    public ResponseEntity<Object> handelUserNotExistException(UserNotExistException e) {

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

        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        ApiException apiException = new ApiException(
                ExceptionMessage.ACCESS_TOKEN_NOT_VALID,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, httpStatus);

    }

    @ExceptionHandler(value = {LoginIdExistException.class})
    public ResponseEntity<Object> handelLoginIdExistException(LoginIdExistException e) {

        HttpStatus httpStatus = HttpStatus.CONFLICT; // 409에러

        ApiException apiException = new ApiException(
                ExceptionMessage.LOGIN_ID_EXIST,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, httpStatus);

    }

    @ExceptionHandler(value = {SmsNumNotValidException.class})
    public ResponseEntity<Object> handelSmsNumNotValidException(SmsNumNotValidException e) {

        HttpStatus httpStatus = HttpStatus.OK; // 409에러

        ApiException apiException = new ApiException(
                ExceptionMessage.SMS_NUM_NOT_VALID,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, httpStatus);

    }


}
