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
    private Long paymentRequestId;    //

    @NotNull
    private int amount;     // 결제 금액

    private String paymentDate;  //

    @Enumerated(EnumType.STRING)
    private Status status;  //

    public void updatePaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

}
