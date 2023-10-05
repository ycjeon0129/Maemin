package com.tft.cartservice;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@RequiredArgsConstructor
public class CartRemoveReq {
	private Long roomId;
	private int index;
}
