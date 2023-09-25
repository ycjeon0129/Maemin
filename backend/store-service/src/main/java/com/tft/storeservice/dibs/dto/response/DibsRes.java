package com.tft.storeservice.dibs.dto.response;

import com.tft.storeservice.dibs.db.entity.Dibs;
import com.tft.storeservice.store.db.entity.Store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class DibsRes {

	private Long storeId;
	private Long userId;

	public DibsRes(Dibs dibs) {
		this.storeId = dibs.getStore().getStoreId();
		this.userId = dibs.getUserId();
	}

}
