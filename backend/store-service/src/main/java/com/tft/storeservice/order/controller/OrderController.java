package com.tft.storeservice.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

// import com.tft.storeservice.order.dto.request.OrderGetReq;
import com.tft.storeservice.order.dto.request.OrderUpdateStatusReq;
import com.tft.storeservice.order.dto.request.OrderReq;
import com.tft.storeservice.order.dto.response.OrderRes;
import com.tft.storeservice.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/customer/order/register")
	public ResponseEntity<String> register(@RequestBody OrderReq orderReq) throws IllegalAccessException {
		Long orderId = orderService.register(orderReq);

		// orderService.notify(orderId);
		return ResponseEntity.ok("orderId : " + orderId);
	}

	@GetMapping("/customer/order/info/{orderId}")
	public ResponseEntity<OrderRes> getInfo(@PathVariable Long orderId){
		return ResponseEntity.ok(orderService.getInfo(orderId));
	}

	@PostMapping("/owner/order/change/status")
	public ResponseEntity<OrderRes> changeInfo(@RequestBody OrderUpdateStatusReq orderUpdateStatusReq){
		return ResponseEntity.ok(orderService.changeStatus(orderUpdateStatusReq));
	}

	@GetMapping("/owner/order/info/{storeId}")
	public ResponseEntity<List<OrderRes>> getOrders(@PathVariable Long storeId){
		return ResponseEntity.ok(orderService.getOrders(storeId));
	}

}
