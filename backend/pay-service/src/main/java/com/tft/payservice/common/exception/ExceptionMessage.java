package com.tft.payservice.common.exception;

public enum ExceptionMessage {
    // 다른 예외 메시지들도 추가 가능
    USER_NOT_EXIST_MESSAGE("User does not exist."),
    ROOM_NOT_EXIST_MESSAGE("Room does not exist."),
    GAME_NOT_EXIST_MESSAGE("Game does not exist."),

    USERNAME_NOT_EXIST_MESSAGE("사용자를 찾을 수 없습니다");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
