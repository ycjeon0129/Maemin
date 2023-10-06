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
import java.security.*;
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

    public static String createKeyPair(RedisTemplate redisTemplate, int index) throws NoSuchAlgorithmException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String stringPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String stringPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        RsaUtil.savePrivateKey(redisTemplate, index, stringPrivateKey);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return stringPublicKey;
    }

    public static void savePrivateKey(RedisTemplate redisTemplate, int index, String privateKey) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        redisTemplate.opsForValue().set(KEY_PAIR + index, privateKey, 60 * 60 * 6 + 60 * 5, TimeUnit.SECONDS);

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    public static PrivateKey getPrivateKey(RedisTemplate redisTemplate, int index) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        String stringPrivateKey = (String) redisTemplate.opsForValue().get(KEY_PAIR+index);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bytePrivateKey = Base64.getDecoder().decode(stringPrivateKey.getBytes());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return privateKey;
    }

    public static String decrypt(String encryptedText, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        String decryptedText = null;

//        try {
//        // 평문으로 전달받은 공개키를 사용하기 위해 공개키 객체 생성
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        byte[] bytePrivateKey = Base64.getDecoder().decode(stringPrivateKey.getBytes());
//        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
//        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

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
