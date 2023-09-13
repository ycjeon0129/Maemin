package com.tft.paymentservice.api.payment.controller;

import com.tft.paymentservice.api.payment.dto.request.PaymentReq;
import com.tft.paymentservice.api.payment.dto.request.RefundReq;
import com.tft.paymentservice.api.payment.dto.response.PaymentLogRes;
import com.tft.paymentservice.api.payment.dto.response.PaymentRes;
import com.tft.paymentservice.api.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class paymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<List<PaymentRes>> createPayment(@RequestBody PaymentReq paymentReq) {
        List<PaymentRes> list = paymentService.createPayment(paymentReq);

//        throw new PaymentNotExistException();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping
    public ResponseEntity<List<PaymentLogRes>> getPaymentLog(@RequestParam int page, @RequestParam int count) {
        List<PaymentLogRes> list = paymentService.getPaymentLog(page, count);

        return ResponseEntity.status(200).body(list);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<List<PaymentRes>> updatePayment(@PathVariable Long orderId, @RequestBody RefundReq refundReq) {
        List<PaymentRes> list = paymentService.updatePayment(orderId, refundReq);

        return ResponseEntity.status(200).body(list);
    }

}
