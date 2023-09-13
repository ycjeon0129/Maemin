package com.tft.paymentservice.api.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PaymentLogRes {

    private String method;      // 결제 수단
    private int amount;         // 결제 금액
    private String store;       // 결제 대상
    private String time;        // 결제 일시 (YYYY-MM-DD HH:mm:SS)

}
