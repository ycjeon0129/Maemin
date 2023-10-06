package com.tft.storeservice.store.controller;

import java.util.List;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.store.dto.request.StoreFindReq;
import com.tft.storeservice.store.dto.request.StoreReq;
import com.tft.storeservice.store.dto.response.StoreAllRes;
import com.tft.storeservice.store.dto.response.StoreRes;
import com.tft.storeservice.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
@RestController
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/stores/{storeId}")
    public ResponseEntity<StoreAllRes> getStoreInfo(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeService.getStoreInfo(storeId));
    }

    @PostMapping("/stores/register")
    public ResponseEntity<StoreRes> register(@RequestBody StoreReq storeReq) {
        return ResponseEntity.ok(storeService.register(storeReq));
    }

    @GetMapping("/test")
    public ResponseEntity<String> getStoreInfo() {
        log.info("성공***************");
        return ResponseEntity.ok("성공");
    }

    @PostMapping("/find")
    public ResponseEntity<List<StoreAllRes>> findStores(@RequestBody StoreFindReq storeFindReq){
        return ResponseEntity.ok(storeService.findStores(storeFindReq));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StoreAllRes>> findAll(){
        return ResponseEntity.ok(storeService.findAll());
    }
}
