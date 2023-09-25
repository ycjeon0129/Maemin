package com.tft.apigatewayservice;

import com.tft.apigatewayservice.handler.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class ApigatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayServiceApplication.class, args);
    }

    @Bean
    public ErrorWebExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

}
