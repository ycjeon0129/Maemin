package com.tft.payservice.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "user-service", url = "${custom.url}"+"/user-service")
public interface UserFeignClient {

    @PutMapping("/users/pay/{status}")
    int joinPay(@PathVariable boolean status);

}
