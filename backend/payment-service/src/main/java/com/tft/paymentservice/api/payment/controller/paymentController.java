package com.tft.paymentservice.api.payment.controller;

import com.tft.paymentservice.api.payment.dto.req.PaymentReq;
import com.tft.paymentservice.api.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class paymentController {

    private final PaymentService paymentService;

    @PostMapping()
    public RequestEntity<?> createPayment(@RequestBody PaymentReq paymentReq) {

        return null;
    }


}
