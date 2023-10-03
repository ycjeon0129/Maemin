package com.tft.userservice.user.dto.response;

import com.tft.userservice.user.db.entity.Bill;
import com.tft.userservice.user.db.entity.User;
import com.tft.userservice.user.dto.request.BillAddReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BillAddRes {

    private Long storeId;
    private String paymentMethod;
    private int totalPrice;
    private String requests;
    private Long userId;

    public BillAddRes() {

    }


    public BillAddRes toRes(BillAddReq billAddReq, Long userId) {

        return new BillAddRes(billAddReq.getStoreId(), billAddReq.getPaymentMethod(), billAddReq.getTotalPrice(),
                billAddReq.getRequests(),userId );
    }

}
