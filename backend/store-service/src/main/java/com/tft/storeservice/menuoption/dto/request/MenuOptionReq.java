package com.tft.storeservice.menuoption.dto.request;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import com.tft.storeservice.menuoption.db.entity.MenuOption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class MenuOptionReq {
    private Long menuId;
    private String option;
    private String content;
    private int price;

    public MenuOption toMenuOption() {
        return MenuOption.builder()
                .option(this.option)
                .content(this.content)
                .price(this.price)
            .createdDate(LocalDateTime.now())
            .status("판매중")
                .build();
    }
}
