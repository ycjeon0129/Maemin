package com.tft.paymentservice.api.payment.dto;

import lombok.Getter;

@Getter
public enum Method {
    TFT_PAY("TFT페이"), KAKAO("카카오"), TOSS("토스");

    private String krName;

    private static Method[] list = Method.values();

    public static String getMethod(int num) {
        return list[num].getKrName();
    }

    Method(String krName) {
        this.krName = krName;
    }
}
