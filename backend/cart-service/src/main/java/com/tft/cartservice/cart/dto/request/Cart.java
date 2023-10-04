package com.tft.cartservice.cart.dto.request;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@NoArgsConstructor
public class Cart {
	private Long teamId;
	private CartMenu cartMenu;
}
