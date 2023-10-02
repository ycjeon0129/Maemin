package com.tft.payservice.common.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static com.tft.payservice.common.util.LogCurrent.*;
import static com.tft.payservice.common.util.LogCurrent.START;

@Slf4j
@Component
public class RsaUtil {


//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    private static final String KEY_PAIR = "key_pair::";

    public static void savePrivateKey(RedisTemplate redisTemplate, int index, String privateKey) {
        redisTemplate.opsForValue().set(KEY_PAIR + index, privateKey, 60 * 60 * 6, TimeUnit.SECONDS);
    }

    public static String getPrivateKey(RedisTemplate redisTemplate, int index) {
        return (String) redisTemplate.opsForValue().get(KEY_PAIR+index);
    }

    public static String decrypt(String encryptedText, String stringPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        String decryptedText = null;

//        try {
            // 평문으로 전달받은 공개키를 사용하기 위해 공개키 객체 생성
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bytePrivateKey = Base64.getDecoder().decode(stringPrivateKey.getBytes());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // 만들어진 공개키 객체로 복호화 설정
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // 암호문을 평문화하는 과정
        byte[] encryptedBytes =  Base64.getDecoder().decode(encryptedText.getBytes());
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        decryptedText = new String(decryptedBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return decryptedText;
    }

}
