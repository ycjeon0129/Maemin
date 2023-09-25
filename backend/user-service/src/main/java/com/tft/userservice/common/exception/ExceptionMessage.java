package com.tft.userservice.common.exception;

public enum ExceptionMessage {

    LOGIN_ID_EXIST("아이디가 존재합니다"),
    
    // 다른 예외 메시지들도 추가 가능
    USER_NOT_EXIST_MESSAGE("사용자를 찾을 수 없습니다"),
    ACCESS_TOKEN_NOT_VALID("Acess-Token not Valid");
    
    
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
