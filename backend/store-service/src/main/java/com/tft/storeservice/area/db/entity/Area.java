package com.tft.storeservice.area.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Area {
    @Id
    private int areaCode;
    private String area;
}
