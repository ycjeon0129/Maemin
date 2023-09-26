package com.tft.storeservice.order.dto.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.tft.storeservice.order.db.entity.Orders;
import com.tft.storeservice.order.db.entity.OrderMenuOption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class OrderReq {
	@NotNull
	private Long storeId;
	@NotNull
	private Long userId;
	private int paymentMethod;
	private String requests;
	private List<Long> menuId = new ArrayList<>();
	private List<OrderMenuOption> menuOptionId = new ArrayList<>();
	private List<Integer> quantity = new ArrayList<>();

	public Orders toOrder(OrderReq orderReq){
		return Orders.builder()
			.storeId(orderReq.storeId)
			.userId(orderReq.userId)
			.paymentMethod(orderReq.paymentMethod)
			.requests(orderReq.requests)
			.menuId(orderReq.menuId)
			.menuOptionId(orderReq.menuOptionId)
			.quantity(orderReq.quantity)
			.createdTime(LocalDateTime.now())
			.build();
	}


}
