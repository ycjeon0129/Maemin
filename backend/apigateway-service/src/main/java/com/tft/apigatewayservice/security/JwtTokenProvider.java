package com.tft.apigatewayservice.security;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${token.access-expired-time}")
    private long ACCESS_EXPIRED_TIME;

    @Value("${token.refresh-expired-time}")
    private long REFRESH_EXPIRED_TIME;

    @Value("${token.secret}")
    private String SECRET;

    // JWT access token 생성
    public String createJwtAccessToken(String userId, String uri, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위

        claims.put("roles", roles); // 정보는 key : value 쌍으로 저장된다.

        return Jwts.builder()
                .addClaims(claims)
                .setExpiration( // set Expire Time
                        new Date(System.currentTimeMillis() + ACCESS_EXPIRED_TIME)
                )
                .setIssuedAt(new Date()) // 토큰 발행 시간 정보
                .signWith(SignatureAlgorithm.HS512, SECRET) // HS512 알고리즘과 signature 에 들어갈 secret 값 세팅
                .setIssuer(uri) // JWT 발급자 지정
                .compact();
    }

    // JWT refresh token 생성
    public String createJwtRefreshToken() {
        Claims claims = Jwts.claims();
        claims.put("value", UUID.randomUUID());

        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(
                        new Date(System.currentTimeMillis() + REFRESH_EXPIRED_TIME)
                )
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUserId(String token) {
        return getClaimsFromJwtToken(token).getSubject();
    }

    public String getRefreshTokenId(String token) {
        return getClaimsFromJwtToken(token).get("value").toString();
    }

    public Date getExpiredTime(String token) {
        return getClaimsFromJwtToken(token).getExpiration();
    }

    public List<String> getRoles(String token) {
        return (List<String>) getClaimsFromJwtToken(token).get("roles");
    }

    // 토큰 정보 검증 메소드 유효성 + 만료 일자 확인
    public void validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        } catch (SignatureException  | MalformedJwtException |
                 UnsupportedJwtException | IllegalArgumentException | ExpiredJwtException jwtException) {
            throw jwtException;
        }
    }

    public boolean equalRefreshTokenId(String refreshTokenId, String refreshToken) {
        String compareToken = this.getRefreshTokenId(refreshToken);
        return refreshTokenId.equals(compareToken);
    }

    private Claims getClaimsFromJwtToken(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}

