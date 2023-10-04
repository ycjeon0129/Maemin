package com.tft.paymentservice.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {


    public static String getToken() {
        String token = null;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        if (attributes != null) {
            String headerData = attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            if (headerData != null) {
                token = headerData;
            }
        }

        return token;
    }

    public static Long getUserId() {
        Long userId = null;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        if (attributes != null) {
            String headerData = attributes.getRequest().getHeader("user-id");
            if (headerData != null) {
                try {
                    userId = Long.parseLong(headerData);  // Ensure it's a valid Long
                } catch (NumberFormatException e) {
                    // Handle the case where user-id is not a valid Long
                    System.err.println("user-id header is not a valid Long: " + userId);
                }
            }
        }
        return userId;
    }

    public static HttpHeaders getHeaders(String key) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", key);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }

}
