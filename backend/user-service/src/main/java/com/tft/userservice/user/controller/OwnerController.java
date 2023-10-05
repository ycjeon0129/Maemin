package com.tft.userservice.user.controller;

import com.tft.userservice.jwt.dto.Result;
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
@RequestMapping("/owners")
public class OwnerController {

    private final UserService userService;

    @PutMapping("/add-store/{storeId}")
    public ResponseEntity<Result> joinPay(@RequestHeader(value = "user-id") String userId,
                                          @PathVariable Long storeId) {
        userService.addStore(userId, storeId);
        return ResponseEntity.ok(Result.createSuccessResult(storeId + "store 추가 성공"));
    }




}
