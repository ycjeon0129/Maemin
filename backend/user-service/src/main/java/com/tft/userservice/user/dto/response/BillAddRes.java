package com.tft.userservice.user.dto.response;

import com.tft.userservice.user.db.entity.Bill;
import com.tft.userservice.user.db.entity.User;
import com.tft.userservice.user.dto.Payment;
import com.tft.userservice.user.dto.request.BillAddReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BillAddRes {

    private String storeName;
    private Payment paymentMethod;
    private int totalPrice;
    private String requests;
    private List<String> menuList;
    private Long userId;

    public BillAddRes() {
        // TODO document why this constructor is empty
    }


    public BillAddRes toRes(BillAddReq billAddReq, Long userId) {

        return new BillAddRes(billAddReq.getStoreName(), Payment.values()[billAddReq.getPaymentMethod()], billAddReq.getTotalPrice(),
                billAddReq.getRequests(), billAddReq.getMenuList(), userId );
    }

}
