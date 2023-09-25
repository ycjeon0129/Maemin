package com.tft.storeservice.store.dto.response;

import java.util.List;

import com.tft.storeservice.dibs.dto.response.DibsRes;
import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.storeImage.db.entity.StoreImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
	private List<StoreImage> pictureUrl;
	private String area;
	// 위도
	private Long latitude;
	// 경도
	private Long longitude;


	// 고쳐야할것
	private int category;
	private double rating;
	private List<Menu> menuList;
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
		this.pictureUrl = store.getStoreImageList();
		this.area = store.getArea().getArea();
		this.latitude = store.getLatitude();
		this.longitude = store.getLongitude();
		this.rating = store.getRating();
		this.category = store.getCategory();
		this.menuList = store.getMenuList();
	}
}
