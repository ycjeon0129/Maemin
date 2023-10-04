package com.tft.storeservice.order.dto.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.tft.storeservice.order.db.entity.Orders;
import com.tft.storeservice.order.db.entity.OrderMenus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class OrderReq {
	@NotNull
	private Long storeId;
	private String requests;
	private int status;
	// 넣어야함
	private List<OrderMenuReq> menus = new ArrayList<>();


	public Orders toOrder(OrderReq orderReq){
		return Orders.builder()
			.storeId(orderReq.storeId)
			.status(orderReq.status)
			.requests(orderReq.requests)
			.createdTime(LocalDateTime.now())
			.build();
	}


}
