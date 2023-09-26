package com.tft.storeservice.dibs.controller;

import com.tft.storeservice.dibs.dto.request.DibsReq;
import com.tft.storeservice.dibs.dto.response.DibsRes;
import com.tft.storeservice.dibs.service.DibsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customer/dibs")
@RequiredArgsConstructor
@Slf4j
@RestController
public class DibsController {
    private final DibsService dibsService;

    @PostMapping("/register")
    public ResponseEntity<DibsRes> register(@RequestBody DibsReq dibsReq) {
        return ResponseEntity.ok(dibsService.register(dibsReq));
    }
}
