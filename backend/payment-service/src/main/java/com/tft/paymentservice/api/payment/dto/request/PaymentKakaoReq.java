package com.tft.paymentservice.api.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PaymentKakaoReq {

    private Long storeId;   // 가게ID
    private Long tableId;   // 주문 테이블 번호
    private Long sessionId; // 주문 세션 고유 ID
    private Long requestId; // 결제요청ID
    private String store;   // 가게 이름
    private int amount;     // 결제금액

}
