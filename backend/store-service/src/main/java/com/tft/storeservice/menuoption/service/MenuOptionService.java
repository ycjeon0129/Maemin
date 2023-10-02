package com.tft.storeservice.menuoption.service;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.menu.service.MenuService;
import com.tft.storeservice.menuoption.db.repository.MenuOptionRepository;
import com.tft.storeservice.menuoption.dto.request.MenuOptionReq;
import com.tft.storeservice.menuoption.dto.response.MenuOptionRes;
import com.tft.storeservice.store.db.entity.Store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MenuOptionService {
    private final MenuOptionRepository menuOptionRepository;
    private final MenuService menuService;

    @Transactional
    public MenuOptionRes register(MenuOptionReq menuOptionReq) {
        return new MenuOptionRes(menuOptionRepository.save(menuOptionReq.toMenuOption()
            .addMenu(getMenu(menuOptionReq.getMenuId()))));
    }

    private Menu getMenu (Long menuId){
        return menuService.getMenu(menuId);
    }
}
