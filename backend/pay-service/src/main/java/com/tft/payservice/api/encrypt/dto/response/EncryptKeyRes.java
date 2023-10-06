package com.tft.payservice.api.encrypt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EncryptKeyRes {

    private int key;            // 공개키 인덱스 번호
    private String value;       // 공개키
    private String validTime;   // RSA 키 페어 유효 시간 (YYYY-MM-DD HH:mm:SS)

}
