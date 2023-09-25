package com.tft.storeservice.dibs.dto.request;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.tft.storeservice.dibs.db.entity.Dibs;
import com.tft.storeservice.store.db.entity.Store;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class DibsReq {
	private Long storeId;
	private Long userId;

	public Dibs toDibs(){
		return Dibs.builder()
			.userId(this.userId)
			.build();

	}
}
