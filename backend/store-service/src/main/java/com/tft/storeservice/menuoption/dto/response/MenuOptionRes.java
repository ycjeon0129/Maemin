package com.tft.storeservice.menuoption.dto.response;

import com.tft.storeservice.menuoption.db.entity.MenuOption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuOptionRes {
    private Long optionId;
    private String option;
    private String content;
    private int price;

    public MenuOptionRes(MenuOption menuOption) {
        optionId = menuOption.getMenuOptionId();
        option = menuOption.getOption();
        content = menuOption.getContent();
        price = menuOption.getPrice();
    }

}
