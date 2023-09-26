package com.tft.storeservice.order.dto.response;

import com.tft.storeservice.menuoption.db.entity.MenuOption;
import com.tft.storeservice.order.dto.request.OrderReq;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderOptionRes {
	private String option;
	private int price;

	public OrderOptionRes toOrderOption(MenuOption menuOption){
		this.option = menuOption.getOption();
		this.price = menuOption.getPrice();
		return this;
	}
}
