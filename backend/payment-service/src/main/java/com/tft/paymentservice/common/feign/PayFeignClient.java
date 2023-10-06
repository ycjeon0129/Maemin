package com.tft.paymentservice.common.feign;

import com.tft.paymentservice.common.dto.PayPaymentReq;
import com.tft.paymentservice.common.dto.PayPaymentRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pay-service", url = "${custom.url}"+"/pay-service")
public interface PayFeignClient {

    //    @PostMapping("/pay/authentication")
//    ResponseEntity<?> authenticationPayment(@RequestBody PayAuthenticationReq payAuthenticationReq);
//    @PostMapping("/pay/confirm")
//    PayConfirmRes confirmPayment(@RequestBody PayConfirmReq payConfirmReq);
//    @PostMapping("/pay/approve")
//    PayApproveRes approvePayment(@RequestBody PayApproveReq payApproveReq);\
    @PostMapping("/pay/payment")
    PayPaymentRes payPayment(@RequestBody PayPaymentReq payPaymentReq);

}
