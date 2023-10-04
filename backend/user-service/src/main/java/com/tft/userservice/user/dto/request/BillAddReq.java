package com.tft.userservice.user.dto.request;

import com.tft.userservice.user.db.entity.Bill;
import com.tft.userservice.user.db.entity.User;
import com.tft.userservice.user.dto.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class BillAddReq {

    private String storeName;
    private int paymentMethod;
    private int totalPrice;
    private String requests;

    //
    private List<String> menuList;


    public Bill toEntity(User user) {
        return Bill.builder()
                .storeName(this.storeName)
                .paymentMethod(Payment.values()[this.paymentMethod])
                .totalPrice(this.totalPrice)
                .requests(this.requests)
                .user(user)
                .build();

    }

}
