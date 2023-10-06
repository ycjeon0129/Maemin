package com.tft.card.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CardApproveReq {

    private Long requestId;     // 결제요청ID
    private String company;     // 결제처
    private String billingKey;  // 빌링키
    private String paymentKey;  // 결제키
    private int amount;         // 결제 가격

}
