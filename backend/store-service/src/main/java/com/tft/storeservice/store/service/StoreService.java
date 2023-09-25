package com.tft.storeservice.store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tft.storeservice.area.db.entity.Area;
import com.tft.storeservice.area.db.repository.AreaRepository;
import com.tft.storeservice.store.db.repository.StoreRepository;
import com.tft.storeservice.store.dto.request.StoreReq;
import com.tft.storeservice.store.dto.response.StoreRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StoreService {

	private final StoreRepository storeRepository;
	private final AreaRepository areaRepository;

	public StoreRes getStoreInfo(Long storeId) {
		return new StoreRes(storeRepository.findById(storeId).orElseThrow());
	}

	public StoreRes register(StoreReq storeReq){
		return new StoreRes(storeRepository.save(storeReq.toStore())
			.addArea(getArea(storeReq)));
	}

	private Area getArea(StoreReq storeReq) {
		return areaRepository.findAreaByAreaCode(storeReq.getAreaCode())
			.orElseThrow(() -> new IllegalArgumentException("id 실수?"));
	}
}
