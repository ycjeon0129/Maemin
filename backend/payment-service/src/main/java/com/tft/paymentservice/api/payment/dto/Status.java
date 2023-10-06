package com.tft.paymentservice.api.payment.dto;

import lombok.Getter;

@Getter
public enum Status {
    PAYMENT_COMPLETE("결제 완료"), PAYMENT_FAIL("결제 실패"), REFUND_REQUEST("환불 요청"), REFUND_COMPLETE("완불 완료");

    private String krName;

    Status(String krName) {
        this.krName = krName;
    }

}
