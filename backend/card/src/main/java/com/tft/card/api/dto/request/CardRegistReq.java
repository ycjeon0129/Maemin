package com.tft.card.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CardRegistReq {

    private String cardNumber;      // 카드 번호 16자리 (1111-1111-1111-1111 형태)
    private String cardExpireDate;  // 카드 유효기간 4자리 (MMYY)
    private String cvc;             // cvc 3자리
    private String password;        // 비밀번호 앞 2자리

}
