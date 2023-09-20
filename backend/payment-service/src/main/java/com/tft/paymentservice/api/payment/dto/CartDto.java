package com.tft.paymentservice.api.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CartDto {

    private Long cardId;    // 선택메뉴ID
    private String menu;    // 메뉴 이름
    private String option;  // 선택 옵션
    private int amount;     // 수량
    private int price;      // 가격

}
