package com.tft.storeservice.area.service;

import com.tft.storeservice.area.db.entity.Area;
import com.tft.storeservice.area.db.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AreaService {
    private final AreaRepository areaRepository;

    public Area getAreaInfo(int areaCode) {
        return areaRepository.findAreaByAreaCode(areaCode);
    }
}
