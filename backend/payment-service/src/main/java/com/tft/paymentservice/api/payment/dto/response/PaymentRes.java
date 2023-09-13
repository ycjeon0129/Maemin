package com.tft.paymentservice.api.payment.dto.response;

import com.tft.paymentservice.api.payment.dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PaymentRes {

    private Long orderId;   // 주문ID
    private String store;   // 상호명
    private String orderer; // 주문자 이름
    private String phone;   // 주문자 전화번호 (010-1234-5667)
    private String request; // 요청사항
    private String status;  // 주문상태
    private int totalPrice; // 가격
    private int type;       // 결제 유형. 0: 일괄 결제, 1: 분할 결제, 2: 커스텀 결제
    List<CartDto> carts;    // 선택 메뉴 배열

}
