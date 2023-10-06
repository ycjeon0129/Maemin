package com.tft.payservice.api.pay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayAuthenticationRes {

    private String code;    // 자체 페이 비밀번호 인증 코드

}
