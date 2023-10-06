package com.tft.payservice.api.encrypt.controller;

import com.tft.payservice.api.encrypt.dto.response.EncryptKeyRes;
import com.tft.payservice.api.encrypt.service.EncryptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

import static com.tft.payservice.common.util.LogCurrent.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/encrypt")
public class EncryptController {

    private final EncryptService encryptService;

    @GetMapping
    public ResponseEntity<EncryptKeyRes> getPublicKey() throws NoSuchAlgorithmException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        EncryptKeyRes encrypt = encryptService.getPublicKey();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(encrypt);
    }

}
