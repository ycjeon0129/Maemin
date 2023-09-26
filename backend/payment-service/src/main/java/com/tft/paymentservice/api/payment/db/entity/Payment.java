package com.tft.paymentservice.api.payment.db.entity;

import com.tft.paymentservice.api.payment.dto.Method;
import com.tft.paymentservice.api.payment.dto.Status;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "payment")
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId; // 결제 식별 ID

    @NotNull
    @Enumerated(EnumType.STRING)
    private Method payMethod; // 결제수단

    @NotNull
    private Long payment_request_id;    //

    @NotNull
    private int amount;     // 결제 금액

    @NotNull
    private String paymentKey;  // 결제 업체로부터 부여받는 값

    private String payment_date;  //

    @Enumerated(EnumType.STRING)
    private Status status;  //

}
