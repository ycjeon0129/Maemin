package com.tft.paymentservice.api.payment.dto.response;

import com.tft.paymentservice.api.payment.dto.CartDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentRes {

//    private Long orderId;   // 주문ID
//    private String store;   // 상호명
//    private String orderer; // 주문자 이름
//    private String phone;   // 주문자 전화번호 (010-1234-5667)
//    private String request; // 요청사항
//    private String status;  // 주문상태
//    private int totalPrice; // 가격
//    private int type;       // 결제 유형. 0: 일괄 결제, 1: 분할 결제, 2: 커스텀 결제
//    List<CartDto> carts;    // 선택 메뉴 배열
    private String method;      // 결제 방법
    private Long requestId;     // 결제 요청 ID
    private int amount;         // 결제 가격
    private String paymentDate;      // 결제 방법
    private String status;      // 결제 상태
    private String authCode;      // 결제 인증 코드

}
