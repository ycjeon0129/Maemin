package com.tft.storeservice.order.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.menuoption.dto.response.MenuOptionRes;
import com.tft.storeservice.order.db.entity.OrderMenuOption;
import com.tft.storeservice.order.db.entity.OrderMenus;
import com.tft.storeservice.order.dto.request.OrderReq;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrderMenuRes {
	private Long menuId;
	private String name;
	private int price;
	private List<OrderOptionRes> menuOptions = new ArrayList<>();
	private int quantity;

	public OrderMenuRes(OrderMenus orderMenus){
		this.menuId = orderMenus.getMenu().getMenuId();
		this.name = orderMenus.getMenu().getName();
		this.quantity = orderMenus.getQuantity();
		this.price = orderMenus.getMenu().getPrice();
		this.menuOptions = orderMenus.getMenuOptions()
			.stream()
			.map(OrderOptionRes::new)
			.collect(Collectors.toList());
	}

	public OrderMenuRes addMenu(Menu menu){
		this.name = menu.getName();
		this.price = menu.getPrice();
		return this;
	}

	public void addOptions(OrderOptionRes orderOptionRes){
		this.menuOptions.add(orderOptionRes);
	}
}
