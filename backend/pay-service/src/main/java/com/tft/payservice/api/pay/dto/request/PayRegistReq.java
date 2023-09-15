package com.tft.payservice.api.pay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayRegistReq {

    private String cardNumber;      // 카드 번호 16자리
    private String cardExpireDate;  // 카드 유효기간 4자리 (MMYY)
    private String cvc;             // cvc 3자리
    private String password;        // 비밀번호 앞 2자리

}
