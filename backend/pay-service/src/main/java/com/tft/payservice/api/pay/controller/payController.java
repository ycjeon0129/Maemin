package com.tft.payservice.api.pay.controller;

import com.tft.payservice.api.pay.dto.request.*;
import com.tft.payservice.api.pay.dto.response.*;
import com.tft.payservice.api.pay.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.NoPermissionException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static com.tft.payservice.common.util.LogCurrent.*;
import static com.tft.payservice.common.util.LogCurrent.END;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pay")
public class payController {

    private final PayService payService;

    @PostMapping("/user")
    public ResponseEntity<?> createPayUser(@RequestHeader("Authorization") String jwt, @RequestBody PayJoinReq payJoinReq) throws Exception {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        payService.createPayUser(payJoinReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping
    public ResponseEntity<PayListRes> getPayList() {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        PayListRes payList = payService.getPayList();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(payList);
    }

    @PostMapping
    public ResponseEntity<?> createPay(@RequestBody PayRegistReq payRegistReq) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        payService.createPay(payRegistReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping("/{payId}")
    public ResponseEntity<?> deletePay(@PathVariable Long payId) throws IOException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        payService.deletePay(payId);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(null);
    }

    @PostMapping("/authentication")
    public ResponseEntity<PayAuthenticationRes> authenticationPayment(@RequestBody PayAuthenticationReq payAuthenticationReq) throws Exception {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        PayAuthenticationRes payAuthentication = payService.authenticationPayment(payAuthenticationReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(payAuthentication);
    }

    @PostMapping("/payment")
    public ResponseEntity<PayPaymentRes> payPayment(@RequestBody PayPaymentReq payPaymentReq) throws IOException, NoPermissionException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        PayPaymentRes payPayment = payService.payPayment(payPaymentReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(payPayment);
    }

//    @PostMapping("/confirm")
//    public ResponseEntity<PayConfirmRes> confirmPayment(@RequestHeader("user-id") Long userId, @RequestBody PayConfirmReq payConfirmReq) {
//        PayConfirmRes payConfirm = payService.confirmPayment(userId, payConfirmReq);
//
//        return ResponseEntity.status(200).body(payConfirm);
//    }
//
//    @PostMapping("/approve")
//    public ResponseEntity<PayApproveRes> approvePayment(@RequestHeader("user-id") Long userId, @RequestBody PayApproveReq payApproveReq) {
//        PayApproveRes payApprove = payService.approvePayment(userId, payApproveReq);
//
//        return ResponseEntity.status(200).body(payApprove);
//    }

}
