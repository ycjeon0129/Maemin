package com.tft.userservice.api.security;

import com.tft.userservice.api.service.CustomUserDetailsService;
import com.tft.userservice.jwt.CookieProvider;
import com.tft.userservice.jwt.JwtTokenProvider;
import com.tft.userservice.jwt.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final CookieProvider cookieProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // rest api이므로 basic auth 및 csrf 보안을 사용하지 않는다는 설정
        http.httpBasic().disable();
        http.csrf().disable();

        // JWT를 사용하기 때문에 세션을 사용하지 않는다는 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/admin**").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/login**").permitAll();

        http.authorizeRequests().anyRequest().permitAll();

        log.info("오류11111111111");

        // Custom Login Authentication 필터를 사용함
        LoginAuthenticationFilter loginAuthenticationFilter =
                new LoginAuthenticationFilter(, jwtTokenProvider, refreshTokenService, cookieProvider);
        loginAuthenticationFilter.setFilterProcessesUrl("/login");

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        System.out.println("오류222222");
        return authenticationConfiguration.getAuthenticationManager();
    }




}
