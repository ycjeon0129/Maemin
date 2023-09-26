package com.tft.paymentservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayPaymentRes {

    private int code;           // HTTP 상태 코드
    private String msg;         // 메세지
    private String payedDate;   // 결제 승인 일시 (YYYY-MM-DD HH:mm:SS)

}
