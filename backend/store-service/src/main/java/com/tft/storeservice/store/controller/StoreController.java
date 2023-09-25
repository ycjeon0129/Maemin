package com.tft.storeservice.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tft.storeservice.store.dto.request.StoreReq;
import com.tft.storeservice.store.dto.response.StoreRes;
import com.tft.storeservice.store.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/store-service/customer")
@RequiredArgsConstructor
@Slf4j
@RestController
public class StoreController {

	private final StoreService storeService;

	@GetMapping("/stores/{storeId}")
	public ResponseEntity<StoreRes> getStoreInfo(@PathVariable Long storeId){
		return ResponseEntity.ok(storeService.getStoreInfo(storeId));
	}

	@PostMapping("/stores/register")
	public ResponseEntity<StoreRes> register(@RequestBody StoreReq storeReq){
		return ResponseEntity.ok(storeService.register(storeReq));
	}
}
