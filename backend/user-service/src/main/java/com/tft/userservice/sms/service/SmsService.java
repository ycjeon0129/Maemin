package com.tft.userservice.sms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tft.userservice.common.exception.custom.SmsNumNotValidException;
import com.tft.userservice.jwt.redis.RefreshToken;
import com.tft.userservice.sms.db.redis.SmsCheck;
import com.tft.userservice.sms.db.repository.SmsCheckRedisRepository;
import com.tft.userservice.sms.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tft.userservice.common.util.LogCurrent.*;
import static com.tft.userservice.common.util.LogCurrent.START;

@Slf4j
@RequiredArgsConstructor
@Service
public class SmsService {

    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;

    //휴대폰 인증 번호
    private final String smsConfirmNum = createSmsKey();
    private final SmsCheckRedisRepository smsCheckRedisRepository;

    public String makeSignature(String time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + this.serviceId + "/messages";
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(time)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);
        log.info(logCurrent(getClassName(), getMethodName(), END));

        return encodeBase64String;
    }

    public SmsRes sendSms(MessageDto messageDto) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        String time = Long.toString(System.currentTimeMillis());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time);
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time)); // signature 서명

        List<MessageDto> messages = new ArrayList<>();
        messages.add(messageDto);

        // api 요청 양식에 맞춰 세팅
        SmsReq request = SmsReq.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phone)
                .content("[푸랜딩] 인증번호 [" + smsConfirmNum + "]를 입력해주세요")
                .messages(messages)
                .build();

        //request를 json형태로 body로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        // jsonBody와 헤더 조립
        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

        //restTemplate를 통해 외부 api와 통신
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        //restTemplate로 post 요청 보내고 오류가 없으면 202코드 반환
        SmsRes smsResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages"), httpBody, SmsRes.class);
//        smsResponseDto.setSmsConfirmNum(smsConfirmNum);
        SmsRes responseDto = new SmsRes(smsConfirmNum);
        log.info("smsResponseDto : reauestid={}, requesttime={}, statuscode={}, statusname={}, smsconfirmnum={}",
                smsResponseDto.getRequestId(), smsResponseDto.getRequestTime(), smsResponseDto.getStatusCode(), smsResponseDto.getStatusName(), smsResponseDto.getSmsConfirmNum());
        log.info("responseDto : {}", responseDto.getSmsConfirmNum());

        log.info("messageTo : {}", messageDto.getTo());

        // redis 에 저장
        smsCheckRedisRepository.save(SmsCheck.of(messageDto.getTo(), responseDto.getSmsConfirmNum()));
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return smsResponseDto;
    }

    public String testSave() {
        MessageDto messageDto = MessageDto.builder().to("01034547282").build();
        String ranNum =createRanKey();
        smsCheckRedisRepository.save(SmsCheck.of(messageDto.getTo(), ranNum));

        return "저장 성공 : " + ranNum;
    }

    public SmsAuthRes checkNum(SmsAuthReq smsAuthReq) {
        SmsCheck smsCheck = smsCheckRedisRepository.findById(smsAuthReq.getTo())
                .orElseThrow(SmsNumNotValidException::new);

        String findOrigNum = smsCheck.getSmsConfirmNum();

        if (!findOrigNum.equals(smsAuthReq.getCheckNum())) {
            return new SmsAuthRes("FAIL");

        }


        return new SmsAuthRes("SUCCESS");
    }


    // 인증코드 만들기
    private static String createSmsKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) { // 인증코드 5자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    private static String createRanKey() {
        Random rnd = new Random();

        String ranKey = String.valueOf(rnd.nextInt(88888) + 11111);

        return ranKey;
    }

}
