package com.tft.card.api.db.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
//@NoArgsConstructor
@DynamicInsert
@Table(name = "card")
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;    // 카드 식별 ID

    @NotNull
    private String cardNumber;  // 카드번호

    @NotNull
    private String cardExpireDate;  // 카드 유효기간

    @NotNull
    private String cvc;     // 카드 CVC

    @NotNull
    private String billingKey;  // 간편 결제를 위한 카드 고유 빌링키

}
