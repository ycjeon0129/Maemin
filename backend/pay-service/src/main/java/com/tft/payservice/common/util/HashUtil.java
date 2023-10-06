package com.tft.payservice.common.util;

import org.springframework.beans.factory.annotation.Value;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class HashUtil {

    private static final int SALT_SIZE = 16;

    public static String hashing(byte[] password, String pepper, String salt, int keyStreching) throws Exception {

        // SHA-256 해시 함수를 사용
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // key-stretching
        for (int i = 0; i < keyStreching; i++) {
            // password에 pepper, salt를 합쳐 새로운 문자열 생성
            String temp = byteToString(password) + pepper + salt;
            // temp의 문자열을 해싱하여 md에 저장
            md.update(temp.getBytes());
            // md 객체의 다이제스트를 얻어 password를 갱신
            password = md.digest();
        }

        return byteToString(password);
    }

    public static String getSalt() throws Exception {
        SecureRandom rnd = new SecureRandom();
        byte[] temp = new byte[SALT_SIZE];
        rnd.nextBytes(temp);

        return byteToString(temp);
    }

    // 바이트 값을 16진수로 변경
    public static String byteToString(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for (byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }

}
