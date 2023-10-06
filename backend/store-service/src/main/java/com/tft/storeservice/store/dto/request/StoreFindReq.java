package com.tft.storeservice.store.dto.request;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class StoreFindReq {
	String areaName;
}
