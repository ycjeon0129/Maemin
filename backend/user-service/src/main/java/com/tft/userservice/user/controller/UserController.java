package com.tft.userservice.user.controller;

import com.tft.userservice.user.dto.request.BillAddReq;
import com.tft.userservice.user.dto.request.IdCheckReq;
import com.tft.userservice.user.dto.request.JoinReq;
import com.tft.userservice.user.dto.response.BillAddRes;
import com.tft.userservice.user.dto.response.BillRes;
import com.tft.userservice.user.dto.response.IdCheckRes;
import com.tft.userservice.user.service.TestService;
import com.tft.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TestService testService;

    @PostMapping("/join")
    public ResponseEntity<String> signup(@RequestBody JoinReq userJoinDto) {
        return ResponseEntity.ok(userService.join(userJoinDto));
    }

    @PostMapping("/check")
    public ResponseEntity<IdCheckRes> checkId(@RequestBody IdCheckReq idCheckReq) {
        return ResponseEntity.ok(userService.checkId(idCheckReq.getCheckId()));
    }

    @PostMapping("/bills")
    public ResponseEntity<BillAddRes> addBills(@RequestHeader(value = "user-id") String userId,
                                               @RequestBody BillAddReq billAddReq) {

        return ResponseEntity.ok(userService.addBills(userId, billAddReq));
    }

    @GetMapping("/bills")
    public ResponseEntity<List<BillRes>> getBills(@RequestHeader(value = "user-id") String userId) {
        return ResponseEntity.ok(userService.getBills(userId));
    }

    @PutMapping("/pay/{status}")
    public ResponseEntity<Integer> joinPay(@RequestHeader(value = "user-id") String userId,
                                           @PathVariable boolean status) {
        return ResponseEntity.ok(userService.joinPay(userId, status));
    }

    @GetMapping(value = "/services")
    public List<String> services() {
        return testService.getServices();
    }

    @GetMapping("/testheader")
        public String getFood(@RequestHeader("user-id") String userId) {
        return userId;
    }


}
