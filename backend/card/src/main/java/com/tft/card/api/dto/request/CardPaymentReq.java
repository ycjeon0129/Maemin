package com.tft.card.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CardPaymentReq {

    private String billingKey;  // 빌링키
    private int amount;         // 결제 가격

}
