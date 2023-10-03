package com.tft.cartservice.cart.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class CartMenu {
	private Long menuId;
	private List<Long> menuOptions = new ArrayList<>();
	private int quantity;
}
