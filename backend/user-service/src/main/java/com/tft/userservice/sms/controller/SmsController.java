package com.tft.userservice.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tft.userservice.sms.dto.MessageDto;
import com.tft.userservice.sms.dto.SmsAuthReq;
import com.tft.userservice.sms.dto.SmsAuthRes;
import com.tft.userservice.sms.dto.SmsRes;
import com.tft.userservice.sms.service.SmsService;
import com.tft.userservice.user.service.TestService;
import com.tft.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sms")
public class SmsController {
    private final SmsService smsService;

    @GetMapping("/test")
    public String saveTest() {
        return smsService.testSave();
    }

    @PostMapping("/send")
    public SmsRes sendSms(@RequestBody MessageDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {

        return smsService.sendSms(messageDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<SmsAuthRes> authSms(@RequestBody SmsAuthReq smsAuthReq) {
        return ResponseEntity.ok(smsService.checkNum(smsAuthReq));
    }


}
