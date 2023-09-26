package com.tft.userservice.sms.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SmsRes {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;
    private String smsConfirmNum;

    public SmsRes(String smsConfirmNum) {
        this.smsConfirmNum = smsConfirmNum;
    }

}
