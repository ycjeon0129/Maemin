package com.tft.userservice.user.dto.request;

import lombok.Data;

@Data
public class LoginReq {
//    private String name;
    private String loginId;
    private String loginPw;
}
