package com.tft.userservice.jwt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    private Code code;
    private String message;
    private T data;
    private T userInfo;

    @Builder
    public Result(Code code, String message, T data, T userInfo) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.userInfo = userInfo;
    }

    public static Result createErrorResult(String message) {
        return Result.builder()
                .code(Code.ERROR)
                .message(message)
                .data(null)
                .build();
    }

    // 해당 <T> 는 클래스의 T와 다름
    public static <T> Result createSuccessResult(T data) {
        return Result.builder()
                .code(Code.SUCCESS)
                .message("")
                .data(data)
                .build();
    }

    public static <T> Result createSuccessLogin(T data, T userInfo) {
        return Result.builder()
                .code(Code.SUCCESS)
                .message("")
                .data(data)
                .userInfo(userInfo)
                .build();
    }
}
