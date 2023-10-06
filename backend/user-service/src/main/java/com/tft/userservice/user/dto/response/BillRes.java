package com.tft.userservice.user.dto.response;

import com.tft.userservice.user.dto.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class BillRes {

    private String storeName;

    private String paymentMethod;

    private int totalPrice;

    private String requests;

    private String createdDate;

    private List<String> menuList;


}
