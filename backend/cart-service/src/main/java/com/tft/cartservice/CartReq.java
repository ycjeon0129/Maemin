package com.tft.cartservice;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@RequiredArgsConstructor
public class CartReq {
	private Long roomId;
	private CartMenu cartMenu;
}
