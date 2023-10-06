package com.tft.cartservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker  // (1)
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {  // (2)
		registry.addEndpoint("/chat")  // (3)
			.setAllowedOrigins("*");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {  // (4)
		registry.enableSimpleBroker("/sub");  // (5)
		registry.setApplicationDestinationPrefixes("/pub"); // (6)
	}
}