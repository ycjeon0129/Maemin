package com.tft.userservice.user.dto.request;

import com.tft.userservice.user.db.entity.Bill;
import com.tft.userservice.user.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class BillAddReq {

    private Long storeId;
    private String paymentMethod;
    private int totalPrice;
    private String requests;

    public Bill toEntity(User user) {
        return Bill.builder()
                .storeId(this.storeId)
                .paymentMethod(this.paymentMethod)
                .totalPrice(this.totalPrice)
                .requests(this.requests)
                .user(user)
                .build();

    }

}
