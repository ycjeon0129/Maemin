package com.tft.userservice.user.db.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long storeId;

    private String paymentMethod;

    private int totalPrice;

    private String requests;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Bill(Long orderId, Long storeId, String paymentMethod, int totalPrice, String requests, LocalDateTime createdDate, User user) {
        this.orderId = orderId;
        this.storeId = storeId;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.requests = requests;
        this.createdDate = createdDate;
        this.user = user;
    }

}
