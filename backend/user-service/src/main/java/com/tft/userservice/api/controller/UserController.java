package com.tft.userservice.api.controller;

import com.tft.userservice.api.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

//    private final UserService userService;
    private final TestService testService;


    @GetMapping(value = "/services")
    public List<String> services() {
        return testService.getServices();
    }

    @GetMapping("/tests")
    public String getFood() {
        return "테스트 성공";
    }

}
