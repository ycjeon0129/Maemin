package com.tft.storeservice.menuoption.dto.request;

import java.util.stream.Collectors;

import com.tft.storeservice.menu.dto.response.MenuRes;
import com.tft.storeservice.menuoption.db.entity.MenuOption;
import com.tft.storeservice.store.db.entity.Store;

public class MenuOptionReq {
	private String option;
	private String content;
	private int price;

	public MenuOption toMenuOption() {
		return MenuOption.builder()
			.option(this.option)
			.content(this.content)
			.price(this.price)
			.build();
		}
}
