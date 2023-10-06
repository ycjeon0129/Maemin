package com.tft.apigatewayservice.filter;

import com.tft.apigatewayservice.security.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    //    private final Environment environment;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthorizationHeaderFilter(JwtTokenProvider jwtTokenProvider) {
        super(Config.class);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(AuthorizationHeaderFilter.Config config) {
        // 첫 번째 매개변수는 ServerWebExchange 형태
        // 두 번째 변수가 GatewayFilterChain 람다 함수
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest(); // Pre Filter
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);

            // Request Header 에서 token 문자열 받아오기
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", "");
            log.info("jwt 토큰 : {}",jwt);
            jwtTokenProvider.validateJwtToken(jwt);

            String subject = jwtTokenProvider.getUserId(jwt);

            log.info(jwtTokenProvider.getRoles(jwt).toString());

            if (!jwtTokenProvider.getRoles(jwt).contains("ROLE_CUSTOMER")) {
                return onError(exchange, "CUSTOMER 권한 없음", HttpStatus.UNAUTHORIZED);
            }

            ServerHttpRequest newRequest = request.mutate()
                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    .header("user-id", subject)
                    .build();

//            return chain.filter(exchange); // 토큰이 일치할때

            return chain.filter(exchange.mutate().request(newRequest).build());

        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }


}