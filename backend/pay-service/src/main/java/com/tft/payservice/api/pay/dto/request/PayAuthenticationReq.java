package com.tft.payservice.api.pay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayAuthenticationReq {

    private String payPw;    // 간편 인증 비밀번호 6자리

}
