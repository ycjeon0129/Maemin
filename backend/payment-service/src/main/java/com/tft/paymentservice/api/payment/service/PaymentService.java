package com.tft.paymentservice.api.payment.service;

import com.tft.paymentservice.api.payment.db.repository.PaymentRepository;
import com.tft.paymentservice.api.payment.dto.Method;
import com.tft.paymentservice.api.payment.dto.Status;
import com.tft.paymentservice.api.payment.dto.request.PaymentReq;
import com.tft.paymentservice.api.payment.dto.request.RefundReq;
import com.tft.paymentservice.api.payment.dto.response.PaymentLogRes;
import com.tft.paymentservice.api.payment.dto.response.PaymentRes;
import com.tft.paymentservice.common.dto.*;
import com.tft.paymentservice.common.feign.PayFeignClient;
import com.tft.paymentservice.common.uitl.RequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tft.paymentservice.common.uitl.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PayFeignClient payFeignClient;
    private final PaymentRepository paymentRepository;
    private final AuthenticationCodeRepository authenticationCodeRepository;

    public PaymentRes createPayment(PaymentReq paymentReq) throws IllegalAccessException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Long userId = RequestUtil.getUserId();
        PaymentRes paymentRes = PaymentRes.builder()    // 디폴트 값: 유효하지 않은 결제 수단인 경우
                .method(Method.getMethod(paymentReq.getMethod()))
                .requestId(paymentReq.getRequestId())
                .amount(paymentReq.getAmount())
                .status(Status.PAYMENT_FAIL.getKrName())
                .build();
        // 자체 페이
        if (paymentReq.getMethod() == 0) {
            PayPaymentReq payReq = PayPaymentReq.builder()
                    .payId(paymentReq.getPayId())
                    .requestId(paymentReq.getRequestId())
                    .amount(paymentReq.getAmount())
                    .code(paymentReq.getCode())
                    .build();
            // Redis에서 authentication code 확인
            AuthenticationCode code = authenticationCodeRepository.findById(paymentReq.getCode())
                    .orElseThrow( () -> new NullPointerException());
            if (!code.getUserId().equals(userId)) {
                throw new IllegalAccessException();
            }
            PayPaymentRes payRes = payFeignClient.payPayment(payReq);

            paymentRes.updatePaymentDate(payRes.getPayedDate());
            paymentRes.updateStatus(Status.PAYMENT_COMPLETE.getKrName());

//            // pay confirm
//            PayConfirmReq payConfirmReq = PayConfirmReq.builder()
//                    .requestId(paymentReq.getRequestId())
//                    .amount(paymentReq.getAmount())
//                    .build();
//            PayConfirmRes confirmRes = payFeignClient.confirmPayment(payConfirmReq);
//
//            // pay approve
//            if (paymentReq.getRequestId().equals(confirmRes.getRequestId()) && paymentReq.getAmount() == confirmRes.getAmount()) {
//                PayApproveReq payApproveReq = PayApproveReq.builder()
//                        .requestId(confirmRes.getRequestId())
//                        .paymentKey(confirmRes.getPaymentKey())
//                        .amount(confirmRes.getAmount())
//                        .build();
//                PayApproveRes approveRes = payFeignClient.approvePayment(payApproveReq);
//
//                paymentRes = PaymentRes.builder()
//                        .paymentDate(approveRes.getPayedDate())
//                        .status(Status.PAYMENT_COMPLETE.getKrName())
//                        .build();
//            } else {
//                log.info(logCurrent(getClassName(), getMethodName(), END));
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Request Information");
//            }
        } else {    // 기타 결제 수단
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return paymentRes;
    }

    public List<PaymentLogRes> getPaymentLog(int page, int count) {
        return null;
    }

    public List<PaymentRes> updatePayment(Long orderId, RefundReq refundReq) {
        return null;
    }
}
