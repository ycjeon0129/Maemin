package com.tft.userservice.user.service;

import com.tft.userservice.user.db.entity.User;
import com.tft.userservice.user.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        User user = userRepository.findByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        // getAccount 줘서 비교하는건지 모르겠음
        return new org.springframework.security.core.userdetails.User(user.getUserId().toString(), user.getLoginPw(), authorities);
    }
}
