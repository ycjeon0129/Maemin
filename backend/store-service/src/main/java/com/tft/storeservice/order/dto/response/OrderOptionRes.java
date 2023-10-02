package com.tft.storeservice.order.dto.response;

import com.tft.storeservice.menuoption.db.entity.MenuOption;
import com.tft.storeservice.order.db.entity.OrderMenuOption;
import com.tft.storeservice.order.dto.request.OrderReq;
import com.tft.storeservice.order.service.OrderService;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrderOptionRes {

	private String option;
	private int price;

	public OrderOptionRes(OrderMenuOption orderMenuOption){
		this.option = orderMenuOption.getMenuOption().getOption();
		this.price = orderMenuOption.getMenuOption().getPrice();
	}
}
