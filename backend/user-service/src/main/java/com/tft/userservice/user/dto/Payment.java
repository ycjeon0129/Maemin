package com.tft.userservice.user.dto;

import lombok.Getter;

@Getter
public enum Payment {
    TFT_PAY("TFT페이"), TOSS("토스");

    private String krName;

    private static Payment[] list = Payment.values();

    public static String getMethod(int num) {
        return list[num].getKrName();
    }

    Payment(String krName) {
        this.krName = krName;
    }
}
