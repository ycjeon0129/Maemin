package com.tft.cartservice.cart.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@NoArgsConstructor
public class CartReq {
	private Long teamId;
	private CartMenu cartMenu;
}
