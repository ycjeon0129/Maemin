package com.tft.payservice.api.pay.dto.response;

import com.tft.payservice.api.pay.dto.PayDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PayListRes {

    private List<PayDto> payList;

}
