package com.tft.userservice.jwt.controller;

import com.tft.userservice.jwt.CookieProvider;
import com.tft.userservice.jwt.dto.Code;
import com.tft.userservice.jwt.dto.JwtTokenDto;
import com.tft.userservice.jwt.dto.Result;
import com.tft.userservice.jwt.service.AccessTokenService;
import com.tft.userservice.jwt.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final AccessTokenService accessTokenService;
    private final CookieProvider cookieProvider;

    @GetMapping("/reissue")
    public ResponseEntity<Result> refreshToken(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                               @CookieValue(value = "refresh-token") String refreshToken) {
        JwtTokenDto jwtTokenDto = refreshTokenService.refreshJwtToken(accessToken, refreshToken);

        ResponseCookie responseCookie = cookieProvider.createRefreshTokenCookie(refreshToken);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(Result.createSuccessResult(new RefreshTokenResponse(jwtTokenDto)));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class RefreshTokenResponse {
        private String accessToken;
        private String expiredTime;

        public RefreshTokenResponse(JwtTokenDto jwtTokenDto) {
            this.accessToken = jwtTokenDto.getAccessToken();
            this.expiredTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(jwtTokenDto.getAccessTokenExpiredDate());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Result> logout(@RequestHeader("X-AUTH-TOKEN") String accessToken) {

        refreshTokenService.logoutToken(accessToken);

        ResponseCookie refreshCookie = cookieProvider.removeRefreshTokenCookie();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(Result.builder().code(Code.SUCCESS).message("로그아웃 성공").data("").build());
//                .body(Result.createSuccessResult(""));
    }

    @GetMapping("/check/access-token")
    public ResponseEntity<Result> checkAccessToken(@RequestHeader(name = "Authorization") String authorization) {

        accessTokenService.checkAccessToken(authorization);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.createSuccessResult(null));
    }
}
