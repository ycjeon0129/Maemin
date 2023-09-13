package com.tft.paymentservice.api.payment.service;

import com.tft.paymentservice.api.payment.dto.request.PaymentReq;
import com.tft.paymentservice.api.payment.dto.request.RefundReq;
import com.tft.paymentservice.api.payment.dto.response.PaymentLogRes;
import com.tft.paymentservice.api.payment.dto.response.PaymentRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    public List<PaymentRes> createPayment(PaymentReq paymentReq) {

        return null;
    }

    public List<PaymentLogRes> getPaymentLog(int page, int count) {
        return null;
    }

    public List<PaymentRes> updatePayment(Long orderId, RefundReq refundReq) {
        return null;
    }
}
