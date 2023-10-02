package com.tft.storeservice.order.dto.request;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class OrderUpdateStatusReq {
	private Long orderId;
	private int status;
}
