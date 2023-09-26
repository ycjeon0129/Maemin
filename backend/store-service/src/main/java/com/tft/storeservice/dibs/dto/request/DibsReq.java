package com.tft.storeservice.dibs.dto.request;

import com.tft.storeservice.dibs.db.entity.Dibs;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class DibsReq {
    private Long storeId;
    private Long userId;

    public Dibs toDibs() {
        return Dibs.builder()
                .userId(this.userId)
                .build();

    }
}
