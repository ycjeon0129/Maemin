package com.tft.userservice.api.dto.request;

import lombok.Data;

@Data
public class LoginReq {
//    private String name;
    private String account;
    private String password;
}
