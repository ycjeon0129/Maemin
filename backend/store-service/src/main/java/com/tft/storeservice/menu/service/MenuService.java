package com.tft.storeservice.menu.service;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.menu.db.repository.MenuRepository;
import com.tft.storeservice.menu.dto.request.MenuReq;
import com.tft.storeservice.menu.dto.response.MenuRes;
import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.store.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MenuService {

	private final MenuRepository menuRepository;
	private final StoreService storeService;

	@Transactional
	public MenuRes register(MenuReq menuReq) {
		return new MenuRes(menuRepository.save(menuReq.toMenu().addStore(getStore(menuReq.getStoreId()))));
	}

	private Store getStore(Long storeId) {
		return storeService.getStore(storeId);
	}

	public Menu getMenu(Long menuId) {
		return menuRepository.findMenuByMenuId(menuId);
	}
}
