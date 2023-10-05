package com.tft.storeservice.menu.dto.response;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.menuoption.dto.response.MenuOptionRes;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MenuRes {

    private Long menuId;
    private Long storeId;
    private String name;
    private int price;
    private String menuPictureUrl;
    private int popularity;
    // private List<MenuOptionRes> menuOptionList;

    // 고쳐야할것
    private String category;

    public MenuRes(Menu menu) {
        this.menuId = menu.getMenuId();
        this.storeId = menu.getStore().getStoreId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.menuPictureUrl = menu.getMenuPictureUrl();
        this.popularity = menu.getPopularity();
        this.category = menu.getCategory();
        // this.menuOptionList = menu.getMenuOptionList()
        //         .stream()
        //         .map(MenuOptionRes::new)
        //         .collect(Collectors.toList());
    }
}
