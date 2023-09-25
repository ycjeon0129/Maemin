package com.tft.storeservice.menuoption.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tft.storeservice.menu.dto.request.MenuReq;
import com.tft.storeservice.menu.dto.response.MenuRes;
import com.tft.storeservice.menuoption.db.repository.MenuOptionRepository;
import com.tft.storeservice.menuoption.dto.request.MenuOptionReq;
import com.tft.storeservice.menuoption.dto.response.MenuOptionRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MenuOptionService {
	private final MenuOptionRepository menuOptionRepository;
	public MenuOptionRes register(MenuOptionReq menuOptionReq){
		return new MenuOptionRes(menuOptionRepository.save(menuOptionReq.toMenuOption()));
	}
}
