package com.tft.cartservice.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tft.cartservice.cart.dto.request.CartMenu;
import com.tft.cartservice.cart.dto.request.CartReq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final Map<Long, List<CartMenu>> sharedCart = new HashMap<>();

	public CartService(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	public void addToCart(CartReq cartReq){
		sharedCart.computeIfAbsent(cartReq.getTeamId(), k -> new ArrayList<>()).add(cartReq.getCartMenu());
		broadcastCartUpdate(cartReq.getTeamId());
	}

	private void broadcastCartUpdate(Long teamId){
		List<CartMenu> updatedCart = sharedCart.get(teamId);
		simpMessagingTemplate.convertAndSend("/topic/cart/" + teamId, updatedCart);
		log.info(updatedCart.toString());
	}
}
