package com.tft.payservice.api.pay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayConfirmRes {

    private int code;           // HTTP 상태 코드
    private String paymentKey;  // 결제키. 승인 시에만 발급
    private Long requestId; // 결제요청ID
    private int amount;     // 결제 가격

}
