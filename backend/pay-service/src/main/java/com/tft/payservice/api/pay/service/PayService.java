package com.tft.payservice.api.pay.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tft.payservice.api.pay.db.entity.Pay;
import com.tft.payservice.api.pay.db.entity.PayUser;
import com.tft.payservice.api.pay.db.repository.PayRepository;
import com.tft.payservice.api.pay.db.repository.PayUserRepository;
import com.tft.payservice.api.pay.dto.PayDto;
import com.tft.payservice.api.pay.dto.request.*;
import com.tft.payservice.api.pay.dto.response.*;
//import com.tft.payservice.common.dto.AuthenticationCode;
//import com.tft.payservice.common.dto.AuthenticationCodeRedisRepository;
//import com.tft.payservice.common.dto.AuthenticationCodeRepository;
import com.tft.payservice.common.feign.UserFeignClient;
import com.tft.payservice.common.util.HashUtil;
import com.tft.payservice.common.util.RandomUtil;
import com.tft.payservice.common.util.RequestUtil;
import com.tft.payservice.common.util.RsaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.NoPermissionException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import static com.tft.payservice.common.util.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayService {

    private final UserFeignClient userFeignClient;
    private final PayRepository payRepository;
    private final PayUserRepository payUserRepository;
//    private final AuthenticationCodeRepository authenticationCodeRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private final String AUTHENTICATION_CODE = "authentication_code::";
    private final String COMPANY = "SF카드";
    @Value("${custom.hash.pepper}")
    private static String PEPPER;
    private static Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    @Value("${custom.card.CARD_URL}")
    private String CARD_URL;

    @Transactional
    public void createPayUser(PayJoinReq payJoinReq) throws Exception {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        Long userId = RequestUtil.getUserId();

        int keyStreching = (int) ((Math.random() * 10000) % 5) + 1;
        String salt = HashUtil.getSalt();
        String hashingPassword = HashUtil.hashing(payJoinReq.getPayPw().getBytes(), PEPPER, salt, keyStreching);
        PayUser user = PayUser.builder()
                .userId(userId)
                .payPw(hashingPassword)
                .salt(salt)
                .keyStreching(keyStreching)
                .build();

        payUserRepository.save(user);

        int status = userFeignClient.joinPay(true);
        if (status != 200) {
            throw new RuntimeException();
        }

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    public PayListRes getPayList() {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        Long userId = RequestUtil.getUserId();

        List<PayDto> payList = new ArrayList<>();
        Optional<PayUser> payUser = payUserRepository.findByUserId(userId);
        if (payUser.isPresent()) {
            List<Pay> list = payUser.get().getPays();

            for (Pay item : list) {
                PayDto pay = PayDto.builder()
                        .payId(item.getPayId())
                        .company(item.getCompany())
                        .basicInfo(item.getBasicInfo())
                        .nickname(item.getNickname())
                        .build();
                payList.add(pay);
            }
        }

        PayListRes res = PayListRes.builder()
                .payList(payList)
                .build();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return res;
    }

    @Transactional
    public void createPay(PayRegistReq payRegistReq) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        Long userId = RequestUtil.getUserId();

        PayUser user = payUserRepository.findByUserId(userId)
                .orElseThrow( () -> new NullPointerException());

        PrivateKey privateKey = RsaUtil.getPrivateKey(redisTemplate, payRegistReq.getKeyIndex());

        // Request Body 생성
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cardNumber", RsaUtil.decrypt(payRegistReq.getCardNumber(), privateKey));
        jsonObject.addProperty("cardExpireDate", RsaUtil.decrypt(payRegistReq.getCardExpireDate(), privateKey));
        jsonObject.addProperty("cvc", RsaUtil.decrypt(payRegistReq.getCvc(), privateKey));
        jsonObject.addProperty("password", RsaUtil.decrypt(payRegistReq.getCardPw(), privateKey));

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
        CardRegistRes body;

        if (200 <= status && status < 300) {

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder res = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    res.append(responseLine.trim());
                }
                body = gson.fromJson(res.toString(), CardRegistRes.class);
            }

            con.disconnect();

            Pay pay = Pay.builder()
                    .payUser(user)
                    .company(COMPANY)
                    .basicInfo(body.getBasicInfo())
                    .nickname(COMPANY + body.getBasicInfo().substring(0, 4))
                    .billingKey(body.getBillingKey())
                    .build();

            payRepository.save(pay);
        } else {
            con.disconnect();
            log.info(logCurrent(getClassName(), getMethodName(), END));
            throw new RuntimeException();
        }

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    @Transactional
    public void deletePay(Long payId) throws IOException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        Long userId = RequestUtil.getUserId();

        Pay pay = payRepository.findByPayId(payId)
                .orElseThrow(); // 없는 payId인 경우 예외 처리

//        if (!pay.getUserId().equals(userId)) {
//            throw new RuntimeException(); // 사용자의 페이가 아닐 경우
//        }

        // Request Param 생성
        String billingKey = pay.getBillingKey();

        // Connection Set
        URL url = new URL(CARD_URL+"/card?billingKey=" + billingKey);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Language", "ko-KR");

        con.setUseCaches(false);    // 캐싱 데이터를 받을지 설정
        con.setDoOutput(false);  // 쓰기 모드 설정

        // Receive Response
        int status = con.getResponseCode();
        con.disconnect();

        if (200 <= status && status < 300) {
            payRepository.delete(pay);
        } else {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            throw new RuntimeException();
        }

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    @Transactional
    public PayAuthenticationRes authenticationPayment(PayAuthenticationReq payAuthenticationReq) throws Exception {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Long userId = RequestUtil.getUserId();

        PayUser user = payUserRepository.findByUserId(userId)
                .orElseThrow(() -> new NullPointerException());

        String submittedPassword = HashUtil.hashing(payAuthenticationReq.getPayPw().getBytes(), PEPPER, user.getSalt(), user.getKeyStreching());

        if (!user.getPayPw().equals(submittedPassword)) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid payPw");
        }

        String code = RandomUtil.excuteGenerate();

        // Redis에 저장
        redisTemplate.opsForValue().set(AUTHENTICATION_CODE+code, userId.toString());
//        AuthenticationCode authenticationCode = AuthenticationCode.builder()
//                .id(code)
//                .userId(userId)
//                .ttl(300)
//                .build();
//        authenticationCodeRepository.save(authenticationCode);

        PayAuthenticationRes payAuthenticationRes = PayAuthenticationRes.builder()
                .code(code)
                .build();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return payAuthenticationRes;
    }

//    public PayConfirmRes confirmPayment(Long userId, PayConfirmReq payConfirmReq) {
//        log.info(logCurrent(getClassName(), getMethodName(), START));
//
//        System.out.println(payConfirmReq.getRequestId());
//        System.out.println(payConfirmReq.getAmount());
//
//        PayConfirmRes confirm = PayConfirmRes.builder()
//                .code(200)
//                .msg("Success!~!~")
//                .paymentKey("asfnkajbnfab")
//                .requestId(payConfirmReq.getRequestId())
//                .amount(payConfirmReq.getAmount())
//                .build();
//
//        log.info(logCurrent(getClassName(), getMethodName(), END));
//        return confirm;
//    }
//
//    public PayApproveRes approvePayment(Long userId, PayApproveReq payApproveReq) {
//        log.info(logCurrent(getClassName(), getMethodName(), START));
//
//        log.info(logCurrent(getClassName(), getMethodName(), END));
//        return null;
//    }

    public PayPaymentRes payPayment(PayPaymentReq payPaymentReq) throws IOException, NoPermissionException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        Long userId = RequestUtil.getUserId();
        System.out.println("userId:::::::::::::::::::::::"+userId);
        System.out.println("userId:::::::::::::::::::::::"+userId.getClass());
        Pay pay = payRepository.findByPayId(payPaymentReq.getPayId())
                .orElseThrow( () -> new NullPointerException() );
        System.out.println("payUserId::::::::::::::::::::"+pay.getPayUser().getUserId());
        System.out.println("payUserId::::::::::::::::::::"+pay.getPayUser().getUserId().getClass());
        if (!pay.getPayUser().getUserId().equals(userId)) {
            throw new NoPermissionException();
        }

        // Request Body 생성
        JsonObject ConfirmJsonObject = new JsonObject();
        ConfirmJsonObject.addProperty("requestId", payPaymentReq.getRequestId());
        ConfirmJsonObject.addProperty("company", "TFT페이");
        ConfirmJsonObject.addProperty("billingKey", pay.getBillingKey());
        ConfirmJsonObject.addProperty("amount", payPaymentReq.getAmount());

        String confirmReq = gson.toJson(ConfirmJsonObject);

        // Connection Set
        URL confirmUrl = new URL(CARD_URL+"/card/confirm");
        HttpURLConnection confirmCon = (HttpURLConnection) confirmUrl.openConnection();
        confirmCon.setRequestMethod("POST");
        confirmCon.setRequestProperty("Content-Type", "application/json; utf-8");
        confirmCon.setRequestProperty("Accept", "application/json");
        confirmCon.setRequestProperty("Content-Length", Integer.toString(confirmReq.getBytes().length));
        confirmCon.setRequestProperty("Content-Language", "ko-KR");

        confirmCon.setUseCaches(false);    // 캐싱 데이터를 받을지 설정
        confirmCon.setDoOutput(true);  // 쓰기 모드 설정

        // Send Request
        try(OutputStream os = confirmCon.getOutputStream()) {
            byte[] input = confirmReq.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Receive Response
        int confirmStatus = confirmCon.getResponseCode();
        PayConfirmRes confirmRes = new PayConfirmRes();

        if (200 <= confirmStatus && confirmStatus < 300) {

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(confirmCon.getInputStream(), "utf-8"))) {
                StringBuilder res = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    res.append(responseLine.trim());
                }
                confirmRes = gson.fromJson(res.toString(), PayConfirmRes.class);
            }

            confirmCon.disconnect();

            if (payPaymentReq.getRequestId().equals(confirmRes.getRequestId()) && payPaymentReq.getAmount() == confirmRes.getAmount()) {
                // Request Body 생성
                JsonObject ApproveJsonObject = new JsonObject();
                ApproveJsonObject.addProperty("requestId", confirmRes.getRequestId());
                ApproveJsonObject.addProperty("company", "TFT페이");
                ApproveJsonObject.addProperty("billingKey", pay.getBillingKey());
                ApproveJsonObject.addProperty("paymentKey", confirmRes.getPaymentKey());
                ApproveJsonObject.addProperty("amount", confirmRes.getAmount());

                String approveReq = gson.toJson(ApproveJsonObject);

                // Connection Set
                URL approveUrl = new URL(CARD_URL+"/card/approve");
                HttpURLConnection approveCon = (HttpURLConnection) approveUrl.openConnection();
                approveCon.setRequestMethod("POST");
                approveCon.setRequestProperty("Content-Type", "application/json; utf-8");
                approveCon.setRequestProperty("Accept", "application/json");
                approveCon.setRequestProperty("Content-Length", Integer.toString(approveReq.getBytes().length));
                approveCon.setRequestProperty("Content-Language", "ko-KR");

                approveCon.setUseCaches(false);    // 캐싱 데이터를 받을지 설정
                approveCon.setDoOutput(true);  // 쓰기 모드 설정

                // Send Request
                try(OutputStream os = approveCon.getOutputStream()) {
                    byte[] input = approveReq.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Receive Response
                int approveStatus = approveCon.getResponseCode();
                PayApproveRes approveRes = new PayApproveRes();

                if (200 <= approveStatus && approveStatus < 300) {

                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(approveCon.getInputStream(), "utf-8"))) {
                        StringBuilder res = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            res.append(responseLine.trim());
                        }
                        approveRes = gson.fromJson(res.toString(), PayApproveRes.class);
                    }

                    approveCon.disconnect();

                    PayPaymentRes payPaymentRes = PayPaymentRes.builder()
                            .code(200)
                            .msg("결제 성공")
                            .payedDate(approveRes.getPayedDate())
                            .build();

                    log.info(logCurrent(getClassName(), getMethodName(), END));
                    return payPaymentRes;
                } else {    // 결제 승인 요청 실패
                    approveCon.disconnect();
                    System.out.println(approveStatus);
                    log.info(logCurrent(getClassName(), getMethodName(), END));
                    throw new RuntimeException();
                }
            } else {    // 결제 인증 요청 중 데이터 위변조
                log.info(logCurrent(getClassName(), getMethodName(), END));
                throw new RuntimeException();
            }
        } else { // 결제 인증 요청 실패
            confirmCon.disconnect();
            log.info(logCurrent(getClassName(), getMethodName(), END));
            throw new RuntimeException();
        }
    }

}