package com.tft.storeservice.sse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.tft.storeservice.sse.Service.SseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class SseController {
	private final SseService sseService;

	@GetMapping("/subscribe/{storeId}")
	public SseEmitter subscribe(@PathVariable Long storeId){
		return sseService.subscribe(storeId);
	}
}
