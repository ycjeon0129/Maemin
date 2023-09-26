package com.tft.storeservice.order.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tft.storeservice.order.db.entity.Orders;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRes {
	private Long orderId;
	private Long storeId;
	private Long userId;
	private int paymentMethod;
	// 넣어야함
	private int totalPrice;
	private String requests;
	private LocalDateTime createdDate;
	// 넣어야함
	private List<OrderMenuRes> menus = new ArrayList<>();

	public OrderRes(Orders orders) {
		this.orderId = orders.getOrderId();
		this.storeId = orders.getStoreId();
		this.userId = orders.getUserId();
		this.paymentMethod = orders.getPaymentMethod();
		this.requests = orders.getRequests();
		this.createdDate = orders.getCreatedTime();
	}

	public OrderRes addPrice(int price){
		this.totalPrice = price;
		return this;
	}

	public void addOrderMenuRes(OrderMenuRes orderMenuRes){
		this.menus.add(orderMenuRes);
	}
}
