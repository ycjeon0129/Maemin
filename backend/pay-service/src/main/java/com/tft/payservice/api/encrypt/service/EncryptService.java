package com.tft.payservice.api.encrypt.service;

import com.tft.payservice.api.encrypt.dto.response.EncryptKeyRes;
import com.tft.payservice.common.util.RandomUtil;
import com.tft.payservice.common.util.RsaUtil;
import com.tft.payservice.common.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import static com.tft.payservice.common.util.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EncryptService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
//    private final String KEY_PAIR = "key_pair::";

    public EncryptKeyRes getPublicKey() throws NoSuchAlgorithmException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

//        SecureRandom secureRandom = new SecureRandom();
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(2048, secureRandom);
//        KeyPair keyPair = keyPairGenerator.genKeyPair();
//
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//
//        String stringPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//        String stringPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
//
//        RsaUtil.savePrivateKey(redisTemplate, index, stringPrivateKey);
        int index = RandomUtil.getRandomNumber();
        String publicKey = RsaUtil.createKeyPair(redisTemplate, index);

        EncryptKeyRes encryptKey = EncryptKeyRes.builder()
                .key(index)
                .value(publicKey)
                .validTime(TimeUtil.parseTimestamp(TimeUtil.getHoursLater(6)))
                .build();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return encryptKey;
    }
}
