package com.tft.payservice.api.pay.controller;

import com.tft.payservice.api.pay.dto.request.*;
import com.tft.payservice.api.pay.dto.response.PayConfirmRes;
import com.tft.payservice.api.pay.dto.response.PayListRes;
import com.tft.payservice.api.pay.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pay")
public class payController {

    private final PayService payService;

    @PostMapping("/user")
    public ResponseEntity<?> createPayUser(@RequestHeader("user-id") Long userId, @RequestBody PayJoinReq payJoinReq) throws Exception {
        payService.createPayUser(userId, payJoinReq);

        return ResponseEntity.status(200).body(null);
    }

    @GetMapping
    public ResponseEntity<PayListRes> getPayList(@RequestHeader("user-id") Long userId) {
        PayListRes payList = payService.getPayList(userId);

        return ResponseEntity.status(200).body(payList);
    }

    @PostMapping
    public ResponseEntity<?> createPay(@RequestHeader("user-id") Long userId, @RequestBody PayRegistReq payRegistReq) throws IOException {
        payService.createPay(userId, payRegistReq);

        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping("/{payId}")
    public ResponseEntity<?> deletePay(@RequestHeader("user-id") Long userId, @PathVariable Long payId) throws IOException {
        payService.deletePay(userId, payId);

        return ResponseEntity.status(200).body(null);
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> authenticationPayment(@RequestHeader("user-id") Long userId, @RequestBody PayAuthenticationReq payAuthenticationReq) throws Exception {
        payService.authenticationPayment(userId, payAuthenticationReq);

        return ResponseEntity.status(200).body(null);
    }

    @PostMapping("/confirm")
    public ResponseEntity<PayConfirmRes> confirmPayment(@RequestHeader("user-id") Long userId, @RequestBody PayConfirmReq payConfirmReq) {
        PayConfirmRes payConfirm = payService.confirmPayment(userId, payConfirmReq);

        return ResponseEntity.status(200).body(payConfirm);
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approvePayment(@RequestHeader("user-id") Long userId, @RequestBody PayApproveReq payApproveReq) {
        payService.approvePayment(userId, payApproveReq);

        return ResponseEntity.status(200).body(null);
    }

}
