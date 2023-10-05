package com.tft.cartservice;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {
	private final SimpMessageSendingOperations messageSendingOperations;
	private final CartService cartService;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectEvent event){
		log.info("Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap((event.getMessage()));
		String sessionId = headerAccessor.getSessionId();

		log.info("sessionId Disconnected : " + sessionId);
	}



	@MessageMapping("/add")
	@SendTo("/topic/cart")
	public void addToCart(@RequestBody CartReq cart){
		cartService.addToCart(cart);
		messageSendingOperations.convertAndSend("/topic/cart/" + cart.getRoomId(), cart.getCartMenu());
	}

	@MessageMapping("/get/{teamId}")
	public ResponseEntity<List<CartMenu>> getCartMenu(@PathVariable Long teamId){
		List<CartMenu> cartMenus = cartService.getCartMenu(teamId);
		return ResponseEntity.ok(cartMenus);
	}
}

