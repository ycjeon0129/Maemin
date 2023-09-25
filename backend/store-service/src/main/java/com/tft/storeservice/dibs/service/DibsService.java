package com.tft.storeservice.dibs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tft.storeservice.dibs.db.repository.DibsRepository;
import com.tft.storeservice.dibs.dto.request.DibsReq;
import com.tft.storeservice.dibs.dto.response.DibsRes;
import com.tft.storeservice.menu.dto.request.MenuReq;
import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.store.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DibsService {
	private final DibsRepository dibsRepository;
	private final StoreService storeService;
	public DibsRes register(DibsReq dibsReq){
		return new DibsRes(dibsRepository
			.save(dibsReq.toDibs()
				.addStore(getStore(dibsReq.getStoreId()))));
	}
	private Store getStore(Long storeId) {
		return storeService.getStore(storeId);
	}


}
