package com.tft.payservice.config;

import com.tft.payservice.common.util.RequestUtil;
import feign.Client;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // log레벨 설정
    }

    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header(HttpHeaders.AUTHORIZATION, RequestUtil.getToken());
            requestTemplate.header("user-id", RequestUtil.getUserId().toString());
        };
    }

}
