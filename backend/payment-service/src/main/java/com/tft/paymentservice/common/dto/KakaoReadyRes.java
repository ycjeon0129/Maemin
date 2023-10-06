package com.tft.paymentservice.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoReadyRes {

    private String tid;
    private String next_redirect_mobile_url;
//    private String partner_order_id;

}
