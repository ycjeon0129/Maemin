package com.tft.userservice.sms.db.redis;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Slf4j
@Getter
@RedisHash(timeToLive = 190)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmsCheck {

    @Id
    private String to;
    private String smsConfirmNum;

    public static SmsCheck of(String to, String smsConfirmNum) {
        SmsCheck smsCheck = new SmsCheck();
        smsCheck.to = to;
        smsCheck.smsConfirmNum = smsConfirmNum;
        log.info("to : {}", smsCheck.getTo());
        log.info("confirmnum : {}", smsCheck.getSmsConfirmNum());
        return smsCheck;
    }
}
