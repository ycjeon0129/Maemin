package com.tft.payservice.api.pay.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class PayRegistReq {

    private String cardNumber;      // [RSA] 카드 번호 16자리
    private String cardExpireDate;  // [RSA] 카드 유효기간 4자리 (MMYY)
    private String cvc;             // [RSA] cvc 3자리
    private String cardPw;          // [RSA] 카드 비밀번호 앞 2자리
    private int keyIndex;           // RSA 공개키 인덱스

}
