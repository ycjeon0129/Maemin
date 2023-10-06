package com.tft.cartservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final Map<Long, List<CartMenu>> sharedCart = new HashMap<>();



	public void addToCart(CartReq cart){
		sharedCart.computeIfAbsent(cart.getRoomId(), k -> new ArrayList<>()).add(cart.getCartMenu());
		broadcastCartUpdate(cart);
	}

	private void broadcastCartUpdate(CartReq cart){
		List<CartMenu> updatedCart = sharedCart.get(cart.getRoomId());
		log.info("/topic/cart/" + cart.getRoomId());
		simpMessagingTemplate.convertAndSend("/topic/cart/" + cart.getRoomId(), updatedCart);
		log.info(updatedCart.toString());
	}

	public List<CartMenu> getCartMenu(Long teamId){
		log.info("get " + sharedCart.getOrDefault(teamId, Collections.emptyList()));
		return sharedCart.getOrDefault(teamId, Collections.emptyList());
	}
}
