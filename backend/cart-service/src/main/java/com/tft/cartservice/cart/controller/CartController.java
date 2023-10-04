package com.tft.cartservice.cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tft.cartservice.cart.dto.request.CartMenu;
import com.tft.cartservice.cart.dto.request.Cart;
import com.tft.cartservice.cart.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/customer/cart")
@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController {
	private final SimpMessageSendingOperations messageSendingOperations;
	private final CartService cartService;


	@MessageMapping("/add")
	public void addToCart(@RequestBody Cart cart){
		cartService.addToCart(cart);
		messageSendingOperations.convertAndSend("topic/cart/" + cart.getTeamId(), cart.getCartMenu());
	}

	@MessageMapping("/get/{teamId}")
	public ResponseEntity<List<CartMenu>> getCartMenu(@PathVariable Long teamId){
		List<CartMenu> cartMenus = cartService.getCartMenu(teamId);
		return ResponseEntity.ok(cartMenus);
	}
}
