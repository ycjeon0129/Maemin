package com.tft.storeservice.storeImage.dto.request;

import java.time.LocalDateTime;

import com.tft.storeservice.storeImage.db.entity.StoreImage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class StoreImageReq {
    private Long storeId;
    private String storePicureUrl;

    public StoreImage toStoreImage() {
        return StoreImage.builder()
            .storePicureUrl(this.storePicureUrl)
            .createdDate(LocalDateTime.now())
            .build();
    }
}
