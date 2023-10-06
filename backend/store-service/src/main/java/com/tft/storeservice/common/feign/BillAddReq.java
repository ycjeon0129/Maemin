package com.tft.storeservice.common.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class BillAddReq {

    private String storeName;
    private int paymentMethod;
    private int totalPrice;
    private String requests;
    private List<String> menuList;

}
