package com.tft.userservice.common.exception;

public enum ExceptionMessage {

    LOGIN_ID_EXIST("아이디가 존재합니다"),
    USER_NOT_EXIST_MESSAGE("사용자를 찾을 수 없습니다"),
    ACCESS_TOKEN_NOT_VALID("Acess-Token not Valid"),
    SMS_NUM_NOT_VALID("SMS 인증 실패");
    
    
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
