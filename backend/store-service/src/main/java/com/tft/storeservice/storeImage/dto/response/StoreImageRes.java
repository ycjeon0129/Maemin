package com.tft.storeservice.storeImage.dto.response;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.storeImage.db.entity.StoreImage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreImageRes {
	private Long storeImageId;
	private Store store;
	private String storePicureUrl;
	public StoreImageRes(StoreImage storeImage){
		this.storeImageId = storeImage.getStoreImageId();
		this.store = storeImage.getStore();
		this.storePicureUrl = storeImage.getStorePicureUrl();
	}
}
