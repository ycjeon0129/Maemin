package com.tft.card.api.db.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "card_pay_log")
@Entity
public class CardPayLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;          // 카드 식별 ID

    @NotNull
    private String company;     // 결제처

    @NotNull
    private String paymentKey;  // 결제키

    private LocalDateTime payedDate;   // 결제 일시

    public void approvePayment() {
        payedDate = LocalDateTime.now();
    }

}

