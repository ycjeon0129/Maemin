package com.tft.userservice.user.db.entity;


import com.tft.userservice.user.dto.Payment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @NotNull
    private String storeName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Payment paymentMethod; // 결제수단

    @NotNull
    private int totalPrice;

    private String requests;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL)
    private List<BillMenu> billMenus;


    @Builder
    public Bill(String storeName, Payment paymentMethod, int totalPrice, String requests, LocalDateTime createdDate, User user) {
        this.storeName = storeName;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.requests = requests;
        this.createdDate = createdDate;
        this.user = user;
    }

}
