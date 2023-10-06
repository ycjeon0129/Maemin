package com.tft.card.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CardRegistRes {

    private String basicInfo;   // 마스킹 된 카드번호
    private String billingKey;  // 빌링키

}
