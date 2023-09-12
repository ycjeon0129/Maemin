package com.tft.userservice.api.service;

import com.tft.userservice.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return (UserDetails) userRepository.findByAccount(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
    }
}
