package com.tft.userservice.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.httpBasic().disable(); // rest api 이므로 기본설정 사용안함.
                                    // 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.

        http.csrf().disable(); // rest api이므로 csrf 보안이 필요없으므로 disable처리.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // jwt token으로 인증할것이므로 세션필요없으므로 생성안함.
        http.authorizeRequests().antMatchers("/admin**").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/login**").permitAll();

        http.authorizeRequests().anyRequest().permitAll();


        return http.build();
    }


}
