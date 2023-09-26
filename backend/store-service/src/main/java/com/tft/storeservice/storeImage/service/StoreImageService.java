package com.tft.storeservice.storeImage.service;

import com.tft.storeservice.store.db.repository.StoreRepository;
import com.tft.storeservice.storeImage.db.repository.StoreImageRepository;
import com.tft.storeservice.storeImage.dto.request.StoreImageReq;
import com.tft.storeservice.storeImage.dto.response.StoreImageRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StoreImageService {
    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;

    public StoreImageRes register(StoreImageReq storeImageReq) {
        return new StoreImageRes(storeImageRepository.save(storeImageReq.toStoreImage()
                .addStore(storeRepository.findStoreByStoreId(storeImageReq.getStoreId()))));
    }
}
