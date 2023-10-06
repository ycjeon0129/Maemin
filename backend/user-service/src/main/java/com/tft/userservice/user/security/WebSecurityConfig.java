package com.tft.userservice.user.security;

import com.tft.userservice.jwt.CookieProvider;
import com.tft.userservice.jwt.JwtTokenProvider;
import com.tft.userservice.jwt.service.RefreshTokenService;
import com.tft.userservice.user.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenServiceImpl;
    private final CookieProvider cookieProvider;
    private final UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Custom Login Authentication 필터를 사용함
        LoginAuthenticationFilter loginAuthenticationFilter =
                new LoginAuthenticationFilter(authenticationManagerBean(), jwtTokenProvider, refreshTokenServiceImpl, cookieProvider, userRepository);
        loginAuthenticationFilter.setFilterProcessesUrl("/login");

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().anyRequest().permitAll();

//        http.logout()
//                .logoutUrl("/logout")
//                .deleteCookies("refresh-token");

        http.addFilter(loginAuthenticationFilter);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}