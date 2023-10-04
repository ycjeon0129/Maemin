package com.tft.payservice.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${custom.url}"+"/user-service")
public interface UserFeignClient {

    @PatchMapping("/users/pay")
    int joinPay(boolean status);

}
