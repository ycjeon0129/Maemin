package com.tft.paymentservice.api.payment.controller;

import com.tft.paymentservice.api.payment.dto.request.PaymentKakaoReq;
import com.tft.paymentservice.api.payment.dto.request.PaymentReq;
import com.tft.paymentservice.api.payment.dto.request.RefundReq;
import com.tft.paymentservice.api.payment.dto.response.PaymentLogRes;
import com.tft.paymentservice.api.payment.dto.response.PaymentRes;
import com.tft.paymentservice.api.payment.service.PaymentService;
import com.tft.paymentservice.common.dto.KakaoReadyRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.tft.paymentservice.common.util.LogCurrent.*;
import static com.tft.paymentservice.common.util.LogCurrent.END;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class paymentController {

    private final PaymentService paymentService;
    @Value("${custom.url}"+"/customer/complete")
    private String REDIRECT_URL;

    @PostMapping
    public ResponseEntity<PaymentRes> createPayment(@RequestBody PaymentReq paymentReq) throws IllegalAccessException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        PaymentRes payment = paymentService.createPayment(paymentReq);

//        throw new PaymentNotExistException();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(payment);
    }

    @PostMapping("/kakao")
    public ResponseEntity<KakaoReadyRes> createKakaoPayment(@RequestBody PaymentKakaoReq paymentKakaoReq) throws IllegalAccessException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        KakaoReadyRes readyRes = paymentService.createKakaoPayment(paymentKakaoReq);

//        throw new PaymentNotExistException();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(readyRes);
    }

    @GetMapping("/kakao/success/{storeId}/{tableId}/{sessionId}")
    public void approveKakaoPayment(HttpServletResponse response, @RequestParam("pg_token") String pgToken
    , @PathVariable Long storeId, @PathVariable Long tableId, @PathVariable Long sessionId) throws IOException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        paymentService.approveKakaoPayment(pgToken, storeId, tableId, sessionId);
        String url = String.format("%s?storeid=%s&tableid=%s&sessionid=%s", REDIRECT_URL, storeId, tableId, sessionId);

        response.sendRedirect(url);

        log.info(logCurrent(getClassName(), getMethodName(), END));
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
