package com.tft.payservice.api.pay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayApproveReq {

    private Long orderId;       // 주문ID
    private String paymentKey;  // 결제키
    private int amount;         // 결제 가격

}
