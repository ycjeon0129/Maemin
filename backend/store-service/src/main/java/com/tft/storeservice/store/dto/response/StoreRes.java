package com.tft.storeservice.store.dto.response;

import com.tft.storeservice.menu.dto.response.MenuRes;
import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.storeImage.dto.response.StoreImageRes;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class StoreRes {

    private Long ownerId;
    private String name;
    private String address;
    private String phone;
    private String content;
    private String operationHours;
    private String closeDays;

    private int dibsCount;
    private List<StoreImageRes> pictureUrl;
    private String area;
    // 위도
    private Long latitude;
    // 경도
    private Long longitude;


    // 고쳐야할것
    private int category;
    private double rating;
    private List<MenuRes> menuList;
    // private int reviewCount;

    public StoreRes(Store store) {
        this.ownerId = store.getStoreId();
        this.name = store.getName();
        this.address = store.getAddress();
        this.phone = store.getPhone();
        this.content = store.getContent();
        this.operationHours = store.getOperationHours();
        this.closeDays = store.getClosedDays();
        this.dibsCount = store.getDibsList().size();
        this.pictureUrl = store.getStoreImageList()
                .stream()
                .map(StoreImageRes::new)
                .collect(Collectors.toList());
        this.area = store.getArea().getArea();
        this.latitude = store.getLatitude();
        this.longitude = store.getLongitude();
        this.rating = store.getRating();
        this.category = store.getCategory();
        this.menuList = store.getMenuList()
                .stream()
                .map(MenuRes::new)
                .collect(Collectors.toList());
    }
}
