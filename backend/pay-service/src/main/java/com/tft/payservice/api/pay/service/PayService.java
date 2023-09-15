package com.tft.payservice.api.pay.service;

import com.tft.payservice.api.pay.dto.request.PayApproveReq;
import com.tft.payservice.api.pay.dto.request.PayConfirmReq;
import com.tft.payservice.api.pay.dto.request.PayRegistReq;
import com.tft.payservice.api.pay.dto.response.PayConfirmRes;
import com.tft.payservice.api.pay.dto.response.PayListRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayService {

    public PayListRes getPayList() {
        return null;
    }

    public void createPay(PayRegistReq payRegistReq) {
    }

    public void deletePay(Long payId) {
    }

    public PayConfirmRes confirmPayment(PayConfirmReq payConfirmReq) {
        return null;
    }

    public void approvePayment(PayApproveReq payApproveReq) {
    }
}
