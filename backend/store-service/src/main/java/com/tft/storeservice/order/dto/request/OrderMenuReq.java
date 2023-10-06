package com.tft.storeservice.order.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class OrderMenuReq {
	private Long menuId;
	private List<Long> menuOptionId = new ArrayList<>();
	private int quantity;
}