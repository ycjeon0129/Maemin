package com.tft.storeservice.order.dto.response;

import java.util.List;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.order.dto.request.OrderReq;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderMenuRes {
	private Long menuId;
	private String name;
	private int price;
	private List<OrderOptionRes> menuOptions;
	private int quantity;

	public OrderMenuRes toOrderMenu(OrderReq orderReq, int index){
		this.menuId = orderReq.getMenuId().get(index);
		this.quantity = orderReq.getQuantity().get(index);

		return this;
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
