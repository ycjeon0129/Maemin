package com.tft.userservice.user.controller;

import com.tft.userservice.user.service.TestService;
import com.tft.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final UserService userService;
    private final TestService testService;

    @GetMapping(value = "/services")
    public List<String> services() {
        return testService.getServices();
    }

    @GetMapping(value = "/header")
    public String headercheck(@RequestHeader(value = "user-id", required = false) Long userId,
                                    @RequestHeader(value = "AUTHORIZATION", required = false) String Auth) {

        if(userId==null){
            return "없음";
        }


        return userId + "\n" + Auth;
    }


}
