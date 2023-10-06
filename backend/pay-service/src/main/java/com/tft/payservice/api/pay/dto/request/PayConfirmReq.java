package com.tft.payservice.api.pay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayConfirmReq {

    private Long requestId;     // 결제요청ID
    private String billingKey;  // 빌링키
    private int amount;         // 결제 가격

}
