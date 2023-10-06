package com.tft.storeservice.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cart-service", url = "https://j9c208.p.ssafy.io/cart-service")
public interface SseFeignClient {

    //    @PostMapping("/pay/authentication")
//    ResponseEntity<?> authenticationPayment(@RequestBody PayAuthenticationReq payAuthenticationReq);
//    @PostMapping("/pay/confirm")
//    PayConfirmRes confirmPayment(@RequestBody PayConfirmReq payConfirmReq);
//    @PostMapping("/pay/approve")
//    PayApproveRes approvePayment(@RequestBody PayApproveReq payApproveReq);\
    @PostMapping("/test/{storeId}/{message}")
    void count(@PathVariable String storeId, @PathVariable String message);

}
