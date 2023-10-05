package com.tft.storeservice.store.service;

import java.util.List;
import java.util.stream.Collectors;

import com.tft.storeservice.area.db.entity.Area;
import com.tft.storeservice.area.db.repository.AreaRepository;
import com.tft.storeservice.area.service.AreaService;
import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.menu.db.repository.MenuRepository;
import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.store.db.repository.StoreRepository;
import com.tft.storeservice.store.dto.request.StoreFindReq;
import com.tft.storeservice.store.dto.request.StoreReq;
import com.tft.storeservice.store.dto.response.StoreAllRes;
import com.tft.storeservice.store.dto.response.StoreRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StoreService {
    private final AreaRepository areaRepository;

    private final StoreRepository storeRepository;
    private final AreaService areaService;
    private final MenuRepository menuRepository;

    public StoreAllRes getStoreInfo(Long storeId) {
        return new StoreAllRes(storeRepository.findById(storeId).orElseThrow());
    }

    public List<StoreAllRes> findStores(StoreFindReq storeFindReq){
        Area area = areaRepository.findByArea(storeFindReq.getAreaName());
        return storeRepository.findAllByArea(area).stream().map(StoreAllRes::new).collect(Collectors.toList());
    }

    public List<StoreAllRes> findAll(){
        return storeRepository.findAll().stream().map(StoreAllRes::new).collect(Collectors.toList());
    }

    @Transactional
    public StoreRes register(StoreReq storeReq) {
        return new StoreRes(storeRepository.save(storeReq.toStore()
                .addArea(getArea(storeReq))));
    }

    private Area getArea(StoreReq storeReq) {
        return areaService.getAreaInfo(storeReq.getAreaCode());
    }

    public Store getStore(Long storeId) {
        return storeRepository.findStoreByStoreId(storeId);
    }


}
