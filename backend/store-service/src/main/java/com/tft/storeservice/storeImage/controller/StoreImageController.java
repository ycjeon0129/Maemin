package com.tft.storeservice.storeImage.controller;

import com.tft.storeservice.storeImage.dto.request.StoreImageReq;
import com.tft.storeservice.storeImage.dto.response.StoreImageRes;
import com.tft.storeservice.storeImage.service.StoreImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/owner/storeImage")
@RequiredArgsConstructor
@Slf4j
@RestController
public class StoreImageController {
    private final StoreImageService storeImageService;

    @PostMapping("/register")
    public ResponseEntity<StoreImageRes> register(@RequestBody StoreImageReq storeImageReq) {
        return ResponseEntity.ok(storeImageService.register(storeImageReq));
    }
}
