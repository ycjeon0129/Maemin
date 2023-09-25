package com.tft.storeservice.menuoption.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tft.storeservice.menu.dto.request.MenuReq;
import com.tft.storeservice.menu.dto.response.MenuRes;
import com.tft.storeservice.menu.service.MenuService;
import com.tft.storeservice.menuoption.dto.request.MenuOptionReq;
import com.tft.storeservice.menuoption.dto.response.MenuOptionRes;
import com.tft.storeservice.menuoption.service.MenuOptionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/owner/menuOption")
@RequiredArgsConstructor
@Slf4j
@RestController
public class MenuOptionController {

	private final MenuOptionService menuOptionService;
	@PostMapping("/register")
	public ResponseEntity<MenuOptionRes> register(@RequestBody MenuOptionReq menuOptionReq){
		return ResponseEntity.ok(menuOptionService.register(menuOptionReq));
	}
}
