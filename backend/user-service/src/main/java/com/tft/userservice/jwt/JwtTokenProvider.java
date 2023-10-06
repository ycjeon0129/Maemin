package com.tft.userservice.jwt;

import com.tft.userservice.common.exception.custom.UserNotExistException;
import com.tft.userservice.user.db.entity.User;
import com.tft.userservice.user.db.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Value("${token.access-expired-time}")
    private long ACCESS_EXPIRED_TIME;

    @Value("${token.refresh-expired-time}")
    private long REFRESH_EXPIRED_TIME;

    @Value("${token.secret}")
    private String SECRET;

    // JWT access token 생성
    public String createJwtAccessToken(String userId, String uri, List<String> roles) {
        User user = userRepository.findByUserId(Long.valueOf(userId)).orElseThrow(UserNotExistException::new);
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위
        claims.put("name", user.getUserName());
        claims.put("nickName", user.getNickName());
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

    // 권한정보 획득
    // Spring Security 인증과정에서 권한확인을 위한 기능
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
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
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            return false;
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

