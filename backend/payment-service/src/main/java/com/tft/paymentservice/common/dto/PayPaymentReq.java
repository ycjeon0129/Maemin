package com.tft.paymentservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayPaymentReq {

    private Long payId;     // 페이ID
    private Long requestId; // 결제요청ID
    private int amount;     // 결제금액
    private String code;    // 자체 페이 인증 코드

}
