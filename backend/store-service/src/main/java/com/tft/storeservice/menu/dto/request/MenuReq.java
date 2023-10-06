package com.tft.storeservice.menu.dto.request;

import java.time.LocalDateTime;

import com.tft.storeservice.menu.db.entity.Menu;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class MenuReq {
    private Long storeId;
    private String category;
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
            .createdDate(LocalDateTime.now())
            .build();
    }
}
