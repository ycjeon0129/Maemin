package com.tft.storeservice.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tft.storeservice.menu.db.repository.MenuRepository;
import com.tft.storeservice.store.dto.request.StoreReq;
import com.tft.storeservice.store.dto.response.StoreRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MenuService {

	private final MenuRepository menuRepository;

	public StoreRes register(StoreReq storeReq){
		return new StoreRes(menuRepository.save(storeReq.toStore());
	}
}
