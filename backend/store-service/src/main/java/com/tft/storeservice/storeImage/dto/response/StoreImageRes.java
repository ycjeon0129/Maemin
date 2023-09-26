package com.tft.storeservice.storeImage.dto.response;

import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.storeImage.db.entity.StoreImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreImageRes {
    private Long storeImageId;
    private String storePicureUrl;

    public StoreImageRes(StoreImage storeImage) {
        this.storeImageId = storeImage.getStoreImageId();
        this.storePicureUrl = storeImage.getStorePicureUrl();
    }
}
