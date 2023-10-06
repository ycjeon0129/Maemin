package com.tft.storeservice.store.dto.request;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;
import com.tft.storeservice.store.db.entity.Store;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class StoreReq {
	private Long ownerId;
	private String name;
	private String category;
	private String address;
	private int areaCode;
	private String phone;
	private String content;
	private String operationHours;
	private String closeDays;
	private String latitude;
	private String longitude;


	public Store toStore() {
		return Store.builder()
			.ownerId(this.ownerId)
			.name(this.name)
			.category(this.category)
			.address(this.address)
			.phone(this.phone)
			.content(this.content)
			.operationHours(this.operationHours)
			.closedDays(this.closeDays)
			.latitude(this.latitude)
			.longitude(this.longitude)
			.createdDate(LocalDateTime.now())
			.status("open")
			.build();
	}
}
