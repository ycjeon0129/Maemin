package com.tft.storeservice.order.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tft.storeservice.order.db.entity.Orders;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRes {
	private Long orderId;
	private Long storeId;
	private int totalPrice;
	private String requests;
	private LocalDateTime createdDate;
	private int status;
	private Long tableId;
	// 넣어야함
	private List<OrderMenuRes> menus = new ArrayList<>();

	public OrderRes(Orders orders) {
		this.orderId = orders.getOrderId();
		this.storeId = orders.getStoreId();
		this.requests = orders.getRequests();
		this.createdDate = orders.getCreatedTime();
		this.totalPrice = orders.getTotalPrice();
		this.status = orders.getStatus();
		this.tableId = orders.getTableId();
		this.menus = orders.getMenuOptionId()
			.stream()
			.map(OrderMenuRes::new)
			.collect(Collectors.toList());
	}

}
