package com.tft.storeservice.storeImage.dto.request;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.store.service.StoreService;
import com.tft.storeservice.storeImage.db.entity.StoreImage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class StoreImageReq {
	private Long storeId;
	private String storePicureUrl;

	public StoreImage toStoreImage(){
		return StoreImage.builder()
			.storePicureUrl(this.storePicureUrl)
			.build();
	}
}
