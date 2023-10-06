package com.tft.cartservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

	private final SimpMessagingTemplate simpMessagingTemplate;  // (1)
	private final Map<Long, List<CartMenu>> sharedCart = new HashMap<>();
	private final Map<Long, Integer> cartMember = new HashMap<>();

	@MessageMapping("/chat/messages")  // (2)
	public void chat(@RequestBody ChattingRequest chattingRequest) {  // (3)
		sharedCart.computeIfAbsent(chattingRequest.getRoomId(), k -> new ArrayList<>()).add(chattingRequest.getCartMenu());
		List<CartMenu> updatedCart = sharedCart.get(chattingRequest.getRoomId());
		simpMessagingTemplate.convertAndSend("/sub/chat/" + chattingRequest.getRoomId(), updatedCart);
		log.info("Message [{}] send by member: {} to chatting room: {}", updatedCart, chattingRequest.getRoomId(), chattingRequest.getRoomId());
	}

	@MessageMapping("/chat/clear")  // (2)
	public void clearChat(@RequestBody CartClearReq cartClearReq) {  // (3)
		Long roomId = cartClearReq.getRoomId();
		sharedCart.computeIfAbsent(roomId, k -> new ArrayList<>());
		List<CartMenu> updatedCart = new ArrayList<>();
		sharedCart.put(roomId, updatedCart);
		cartMember.put(roomId, 0);
		simpMessagingTemplate.convertAndSend("/sub/chat/" + roomId, "clear cart");
		log.info("Message [{}] send by member: {} to clear room", updatedCart, roomId);
	}

	@MessageMapping("/chat/remove")
	public void removeCart(@RequestBody CartRemoveReq cartRemoveReq){
		Long roomId = cartRemoveReq.getRoomId();
		int index = cartRemoveReq.getIndex();
		List<CartMenu> updatedCart = sharedCart.get(roomId);
		updatedCart.remove(index);
		simpMessagingTemplate.convertAndSend("/sub/chat/" + roomId, updatedCart);
		log.info("Message [{}] send by member: {} to clear room", updatedCart, roomId);
	}

	@MessageMapping("/chat/complete")
	public void complete(@RequestBody CartClearReq cartClearReq){
		Long roomId = cartClearReq.getRoomId();
		int complete = cartMember.get(roomId);
		complete ++;
		cartMember.put(roomId, complete);
		simpMessagingTemplate.convertAndSend("/sub/chat/" + roomId, complete);
		log.info("Message [{}] send by member: {} to complete pay", complete, roomId);
	}
}