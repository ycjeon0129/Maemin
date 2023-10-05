package com.tft.storeservice.store.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.menu.dto.response.MenuRes;
import com.tft.storeservice.storeImage.dto.response.StoreImageRes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class StoreRes {

	private Long storeId;
	private Long ownerId;
	private String name;
	private String address;
	private String phone;
	private String content;
	private String operationHours;
	private String closeDays;

	private int dibsCount;
	private List<StoreImageRes> pictureUrl = new ArrayList<>();
	private int area;
	// 위도
	private String latitude;
	// 경도
	private String longitude;


	// 고쳐야할것
	private String category;
	private double rating;
	private List<MenuRes> menuList = new ArrayList<>();
	// private int reviewCount;

	public StoreRes(Store store) {
		this.storeId = store.getStoreId();
		this.ownerId = store.getOwnerId();
		this.name = store.getName();
		this.address = store.getAddress();
		this.phone = store.getPhone();
		this.content = store.getContent();
		this.operationHours = store.getOperationHours();
		this.closeDays = store.getClosedDays();
		this.area = store.getArea().getAreaCode();
		this.latitude = store.getLatitude();
		this.longitude = store.getLongitude();
		// this.rating = store.getRating();
		this.category = store.getCategory();

	}
}
