package com.tft.cartservice;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CartMenu {
	private Long userId;
	private Long menuId;
	private List<Long> menuOptionId = new ArrayList<>();
	private int quantity;
}
