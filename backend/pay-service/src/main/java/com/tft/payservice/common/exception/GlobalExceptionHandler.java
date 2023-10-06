package com.tft.payservice.common.exception;

import com.tft.payservice.common.exception.custom.PayNotExistException;
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

    @ExceptionHandler(value = {PayNotExistException.class})
    public ResponseEntity<Object> handelGameNotExistException(PayNotExistException e) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                ExceptionMessage.GAME_NOT_EXIST_MESSAGE,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, httpStatus);

    }

}
