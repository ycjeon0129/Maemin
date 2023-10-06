package com.tft.userservice.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tft.userservice.common.exception.custom.UserNotExistException;
import com.tft.userservice.user.db.repository.UserRepository;
import com.tft.userservice.user.dto.request.LoginReq;
import com.tft.userservice.jwt.CookieProvider;
import com.tft.userservice.jwt.JwtTokenProvider;
import com.tft.userservice.jwt.dto.Result;
import com.tft.userservice.jwt.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Slf4j
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final CookieProvider cookieProvider;
    private final UserRepository userRepository;

    // login 리퀘스트 로 오는 요청을 판단
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        Authentication authentication;
        try{
            // POST 요청으로 들어오는 loginId 와 loginPw
            log.info("1. loginId, loginPw 받는다.");
            LoginReq credential = new ObjectMapper().readValue(request.getInputStream(), LoginReq.class);
            log.info(credential.toString());

            // UsernamePasswordAuthenticationToken은 Spring이 Authentication logic에 사용할 Token
            // loadUserByUsername 메소드에서 로그인 판별
            // account 와 password를 이용해 Authentication 타입의 토큰 생성
            log.info("->> Authenticate Start");
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credential.getLoginId(), credential.getLoginPw())
            );
            log.info("로그인 성공 후 principal : {}", authentication.getPrincipal().toString());

            log.info("<<-- Authenticate End");
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return authentication;
    }

    // login 성공 이후 토큰 생성
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException{
        User user = (User) authResult.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // userId, loginId 아님
        String userId = user.getUsername();
        log.info("1. accesstoken 넘겨줄 userId : {}", userId);

        // response body에 넣어줄 access token 및 expired time 생성
        String accessToken = jwtTokenProvider.createJwtAccessToken(userId, request.getRequestURI(), roles);
        Date expiredTime = jwtTokenProvider.getExpiredTime(accessToken);
        // 쿠키에 넣어줄 refresh token 생성
        String refreshToken = jwtTokenProvider.createJwtRefreshToken();

        // redis에 새로 발행된 refresh token 값 갱신
        refreshTokenService.updateRefreshToken(Long.valueOf(userId), jwtTokenProvider.getRefreshTokenId(refreshToken));

        // 쿠키 설정
        ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie(refreshToken);

        Cookie cookie = cookieProvider.of(refreshTokenCookie);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.addCookie(cookie);

        com.tft.userservice.user.db.entity.User curUser = userRepository.findByUserId(Long.valueOf(userId)).orElseThrow(UserNotExistException::new);

        Map<String, Object> userInfo = Map.of(

                "userName", curUser.getUserName(),
                "nickName", curUser.getNickName(),
                "pay", curUser.isPay(),
                "storeId", curUser.getStoreId()
        );
        // body 설정
        Map<String, Object> tokens = Map.of(
                "accessToken", accessToken,
                "expiredTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiredTime)
        );

        new ObjectMapper().writeValue(response.getOutputStream(), Result.createSuccessLogin(tokens, userInfo));

//        new ObjectMapper().writeValue(response.getOutputStream(), Result.createSuccessResult(tokens));


    }



}
