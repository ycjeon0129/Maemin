package com.tft.payservice.api.pay.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tft.payservice.common.util.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;
    private static Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    @Value("${custom.card.CARD_URL}")
    private String CARD_URL;

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

    public void createPay(PayRegistReq payRegistReq) throws IOException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

//        String map = "{\"cardNumber\":\"1234-5678-****-****\", \"cardNumber\":\"1234\", \"cvc\":\"123\", \"password\":\"123456\"}";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cardNumber", payRegistReq.getCardNumber());
        jsonObject.addProperty("cardExpireDate", payRegistReq.getCardExpireDate());
        jsonObject.addProperty("cvc", payRegistReq.getCvc());
        jsonObject.addProperty("password", payRegistReq.getCardPW());

        String requestBody = gson.toJson(jsonObject);

        // Connection Set
        URL url = new URL(CARD_URL+"/card");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Length", Integer.toString(requestBody.getBytes().length));
        con.setRequestProperty("Content-Language", "ko-KR");

        con.setUseCaches(false);    // 캐싱 데이터를 받을지 설정
        con.setDoOutput(true);  // 쓰기 모드 설정

        // Send Request
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Receive Response
        int status = con.getResponseCode();
        if (200 <= status && status < 300) {

            //
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder res = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    res.append(responseLine.trim());
                }
                System.out.println("00"+res.toString());
            }

            //
            //

//            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            StringBuilder res = new StringBuilder();
//            String inputLine;
////            while ((line = br.readLine()) != null) {
////                res.append(line);
////                res.append('\r');
////            }
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = br.readLine()) != null) {
//                content.append(inputLine);
//            }
//            br.close();
            con.disconnect();
//            System.out.println(res.toString());
        }

        System.out.println(status);

        log.info(logCurrent(getClassName(), getMethodName(), END));
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
