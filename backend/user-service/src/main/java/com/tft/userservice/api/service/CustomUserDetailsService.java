package com.tft.userservice.api.service;

import com.tft.userservice.db.entity.User;
import com.tft.userservice.db.repository.UserRepository;
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
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        User user = userRepository.findByAccount(account).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));

//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getDtype()));

        // getAccount 줘서 비교하는건지 모르겠음
        return new org.springframework.security.core.userdetails.User(user.getUserId().toString(), user.getUserPw(), new ArrayList<>());
    }
}
