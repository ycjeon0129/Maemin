package com.tft.payservice.api.pay.service;

import com.tft.payservice.api.pay.db.entity.Pay;
import com.tft.payservice.api.pay.db.repository.PayRepository;
import com.tft.payservice.api.pay.dto.PayDto;
import com.tft.payservice.api.pay.dto.request.PayApproveReq;
import com.tft.payservice.api.pay.dto.request.PayAuthenticationReq;
import com.tft.payservice.api.pay.dto.request.PayConfirmReq;
import com.tft.payservice.api.pay.dto.request.PayRegistReq;
import com.tft.payservice.api.pay.dto.response.PayConfirmRes;
import com.tft.payservice.api.pay.dto.response.PayListRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tft.payservice.common.util.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;

    public PayListRes getPayList() {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        /**
         *  JWT or ContextHolder에서 yserId 추출하여 사용
         */
        Long userId = 1L;

        List<Pay> list = payRepository.findByUserId(userId);
        List<PayDto> payList = new ArrayList<>();

        for (Pay item : list) {
            PayDto pay = PayDto.builder()
                    .payId(item.getPayId())
                    .company(item.getCompany())
                    .basicInfo(item.getBasicInfo())
                    .nickname(item.getNickname())
                    .build();
            payList.add(pay);
        }

        PayListRes res = PayListRes.builder()
                .payList(payList)
                .build();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return res;
    }

    public void createPay(PayRegistReq payRegistReq) {
    }

    public void deletePay(Long payId) {
    }

    public void authenticationPayment(PayAuthenticationReq payAuthenticationReq) {
    }

    public PayConfirmRes confirmPayment(PayConfirmReq payConfirmReq) {
        return null;
    }

    public void approvePayment(PayApproveReq payApproveReq) {
    }

}
