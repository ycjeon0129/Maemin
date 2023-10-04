package com.tft.cartservice.cart.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tft.cartservice.cart.dto.request.CartMenu;
import com.tft.cartservice.cart.dto.request.Cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final Map<Long, List<CartMenu>> sharedCart = new HashMap<>();



	public void addToCart(Cart cart){
		sharedCart.computeIfAbsent(cart.getTeamId(), k -> new ArrayList<>()).add(cart.getCartMenu());
		broadcastCartUpdate(cart.getTeamId());
	}

	private void broadcastCartUpdate(Long teamId){
		List<CartMenu> updatedCart = sharedCart.get(teamId);
		log.info("/topic/cart/" + teamId);
		simpMessagingTemplate.convertAndSend("/topic/cart/" + teamId, updatedCart);
		log.info(updatedCart.toString());
	}

	public List<CartMenu> getCartMenu(Long teamId){
		log.info("get " + sharedCart.getOrDefault(teamId, Collections.emptyList()));
		return sharedCart.getOrDefault(teamId, Collections.emptyList());
	}
}
