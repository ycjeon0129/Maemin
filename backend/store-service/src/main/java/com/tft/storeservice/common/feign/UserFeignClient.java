package com.tft.storeservice.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "https://j9c208.p.ssafy.io/user-service")
public interface UserFeignClient {

    @PostMapping("/users/bills")
    void addBills(@RequestBody BillAddReq billAddReq);

}
