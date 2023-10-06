package com.tft.storeservice.dibs.dto.response;

import com.tft.storeservice.dibs.db.entity.Dibs;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DibsRes {

    private Long storeId;
    private Long userId;

    public DibsRes(Dibs dibs) {
        this.storeId = dibs.getStore().getStoreId();
        this.userId = dibs.getUserId();
    }

}
