package com.tft.paymentservice.api.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PaymentReq {

    private Long requestId; // 결제요청ID
    private String store;   // 가게 이름
//    private int type;       // 결제 종류. 0: 일반 결제, 1: 분할 결제, 2: 커스텀 결제
    private int amount;     // 결제금액
//    private int method;     // 결제 수단. 0: 자체 페이, 1: 토스, ...

    // 자체 페이 시 사용. 아니라면 null
    private Long payId;     // 페이ID
    private String code;    // 페이 인증 코드

}
