package com.tft.userservice.jwt.service;

import com.tft.userservice.common.exception.custom.AccessTokenNotValidException;
import com.tft.userservice.common.exception.custom.UserNotExistException;
import com.tft.userservice.user.db.entity.User;
import com.tft.userservice.user.db.repository.UserRepository;
import com.tft.userservice.jwt.JwtTokenProvider;
import com.tft.userservice.jwt.dto.JwtTokenDto;
import com.tft.userservice.jwt.exception.RefreshTokenNotValidException;
import com.tft.userservice.jwt.redis.RefreshToken;
import com.tft.userservice.jwt.repository.RefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.tft.userservice.common.util.LogCurrent.*;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RefreshTokenService {

    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Transactional
    public void updateRefreshToken(Long id, String uuid) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        User user = userRepository.findByUserId(id)
                .orElseThrow(UserNotExistException::new);

        refreshTokenRedisRepository.save(RefreshToken.of(user.getUserId(), uuid));
        log.info("refresh key : {}", user.getUserId());
        log.info("refresh value : {}", uuid);
        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    @Transactional
    public JwtTokenDto refreshJwtToken(String accessToken, String refreshToken) {
        String userId = jwtTokenProvider.getUserId(accessToken);
        log.info("refresj JWT 토큰 : {}", userId);
        RefreshToken findRefreshToken = refreshTokenRedisRepository.findById(Long.valueOf(userId))
                .orElseThrow(()
                        -> new RefreshTokenNotValidException("사용자 고유번호 : " + userId + "는 등록된 리프레쉬 토큰이 없습니다.")
                );

        // refresh token 검증
        String findRefreshTokenId = findRefreshToken.getRefreshTokenId();
        if (!jwtTokenProvider.validateJwtToken(refreshToken)) {
            refreshTokenRedisRepository.delete(findRefreshToken);
            throw new RefreshTokenNotValidException("Not validate jwt token = " + refreshToken);
        }

        if (!jwtTokenProvider.equalRefreshTokenId(findRefreshTokenId, refreshToken)) {
            throw new RefreshTokenNotValidException("redis 의 값과 일치하지 않습니다. = " + refreshToken);
        }

        User findUser = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(UserNotExistException::new);
//                .orElseThrow(() -> new NotExistUserException("유저 고유 번호 : " + userId + "는 없는 유저입니다."));

        // access token 생성
        Authentication authentication = getAuthentication(findUser.getLoginId());

        List<String> roles = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String newAccessToken = jwtTokenProvider.createJwtAccessToken(userId, "/reissu", roles);
        Date expiredTime = jwtTokenProvider.getExpiredTime(newAccessToken);

        return JwtTokenDto.builder()
                .accessToken(newAccessToken)
                .accessTokenExpiredDate(expiredTime)
                .refreshToken(refreshToken)
                .build();
    }

    public void logoutToken(String accessToken) {
        if (!jwtTokenProvider.validateJwtToken(accessToken)) {
            // 예외 발생
            throw new AccessTokenNotValidException();
        }

        RefreshToken refreshToken = refreshTokenRedisRepository.findById(Long.valueOf(jwtTokenProvider.getUserId(accessToken)))
                .orElseThrow(() -> new RefreshTokenNotValidException("refresh Token is not exist"));

        refreshTokenRedisRepository.delete(refreshToken);
    }

    public Authentication getAuthentication(String loginId) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
