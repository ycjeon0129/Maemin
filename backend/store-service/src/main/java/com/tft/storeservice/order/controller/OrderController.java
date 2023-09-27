package com.tft.storeservice.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tft.storeservice.order.dto.request.OrderGetReq;
import com.tft.storeservice.order.dto.request.OrderReq;
import com.tft.storeservice.order.dto.response.OrderRes;
import com.tft.storeservice.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/customer/order")
@RequiredArgsConstructor
@Slf4j
@RestController
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/register")
	public ResponseEntity<OrderRes> register(@RequestBody OrderReq orderReq){
		return ResponseEntity.ok(orderService.register(orderReq));
	}

	@GetMapping("/info/{orderId}")
	public ResponseEntity<OrderRes> getInfo(@PathVariable Long orderId){
		return ResponseEntity.ok(orderService.getInfo(orderId));
	}

	// @GetMapping("/info/{orderId}")
	// public ResponseEntity<OrderRes> getInfo(@PathVariable Long orderId){
	// 	return ResponseEntity.ok(orderService.getInfo(orderId));
	// }
	//
	// @PostMapping("/info")
	// public ResponseEntity<OrderRes> getInfo(@RequestBody OrderGetReq orderGetReq){
	// 	return ResponseEntity.ok(orderService.getInfoWithUserId(orderGetReq.getStoreId(), orderGetReq.getUserId()));
	// }
}
