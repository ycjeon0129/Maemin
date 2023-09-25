package com.tft.storeservice.menu.dto.request;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;
import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.store.dto.request.StoreReq;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class MenuReq {
	private Long storeId;
	private int category;
	private String name;
	private int price;
	private String menuPictureUrl;
	private int popularity;


	public Menu toMenu() {
		return Menu.builder()
			.name(this.name)
			.category(this.category)
			.price(this.price)
			.menuPictureUrl(this.menuPictureUrl)
			.popularity(this.popularity)
			.build();
	}
}
