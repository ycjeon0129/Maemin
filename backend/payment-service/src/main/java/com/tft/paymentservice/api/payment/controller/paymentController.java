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

import static com.tft.paymentservice.common.util.LogCurrent.*;
import static com.tft.paymentservice.common.util.LogCurrent.END;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class paymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentRes> createPayment(@RequestBody PaymentReq paymentReq) throws IllegalAccessException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        PaymentRes payment = paymentService.createPayment(paymentReq);

//        throw new PaymentNotExistException();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(payment);
    }

    @GetMapping
    public ResponseEntity<List<PaymentLogRes>> getPaymentLog(@RequestParam int page, @RequestParam int count) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        List<PaymentLogRes> list = paymentService.getPaymentLog(page, count);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(list);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<List<PaymentRes>> updatePayment(@PathVariable Long orderId, @RequestBody RefundReq refundReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        List<PaymentRes> list = paymentService.updatePayment(orderId, refundReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(list);
    }

}
