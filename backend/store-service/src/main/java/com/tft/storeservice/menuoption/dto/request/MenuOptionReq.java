package com.tft.storeservice.menuoption.dto.request;

import com.tft.storeservice.menuoption.db.entity.MenuOption;

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
