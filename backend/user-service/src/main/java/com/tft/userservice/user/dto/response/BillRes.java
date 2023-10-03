package com.tft.userservice.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BillRes {

    private Long billId;

    private Long storeId;

    private String paymentMethod;

    private int totalPrice;

    private String requests;

    private LocalDateTime createdDate;


}
