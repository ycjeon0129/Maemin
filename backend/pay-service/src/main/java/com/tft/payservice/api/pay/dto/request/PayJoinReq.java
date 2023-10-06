package com.tft.payservice.api.pay.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class PayJoinReq {

    private String payPw;           // 간편 인증 비밀번호 6자리

}
