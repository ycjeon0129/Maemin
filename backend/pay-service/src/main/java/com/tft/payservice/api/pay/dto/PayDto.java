package com.tft.payservice.api.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayDto {

    private Long payId;         // 페이ID
    private String company;     // 카드사
    private String basicInfo;   // 카드식별정보 (마스킹된 카드번호 일부)
    private String nickname;    // 카드별명

}
