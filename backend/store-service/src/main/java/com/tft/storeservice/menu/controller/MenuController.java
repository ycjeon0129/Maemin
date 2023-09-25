package com.tft.storeservice.menu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tft.storeservice.menu.dto.request.MenuReq;
import com.tft.storeservice.menu.dto.response.MenuRes;
import com.tft.storeservice.menu.service.MenuService;
import com.tft.storeservice.store.dto.request.StoreReq;
import com.tft.storeservice.store.dto.response.StoreRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/owner/menu")
@RequiredArgsConstructor
@Slf4j
@RestController
public class MenuController {

	private final MenuService menuService;
	@PostMapping("/register")
	public ResponseEntity<MenuRes> register(@RequestBody MenuReq menuReq){
		return ResponseEntity.ok(menuService.register(menuReq));
	}
}
