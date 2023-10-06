package com.tft.storeservice.menu.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tft.storeservice.menuoption.db.entity.MenuOption;
import com.tft.storeservice.order.db.entity.OrderMenus;
import com.tft.storeservice.store.db.entity.Store;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy = "menu", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"menu"})
    private List<MenuOption> menuOptionList = new ArrayList<>();

    @OneToMany(mappedBy = "menu", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"menu"})
    private List<OrderMenus> orderMenus = new ArrayList<>();

    @NotNull
    private String category;

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private String menuPictureUrl;

    @NotNull
    private int popularity;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    public Menu addStore(Store store) {
        this.store = store;
        return this;
    }
}
