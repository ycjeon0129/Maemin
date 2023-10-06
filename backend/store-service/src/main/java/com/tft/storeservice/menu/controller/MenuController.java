package com.tft.storeservice.menu.controller;

import com.tft.storeservice.menu.dto.request.MenuReq;
import com.tft.storeservice.menu.dto.response.MenuRes;
import com.tft.storeservice.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/owner/menu")
@RequiredArgsConstructor
@Slf4j
@RestController
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/register")
    public ResponseEntity<MenuRes> register(@RequestBody MenuReq menuReq) {
        return ResponseEntity.ok(menuService.register(menuReq));
    }
}
