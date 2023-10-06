package com.tft.storeservice.menuoption.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.order.db.entity.OrderMenuOption;

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
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuOptionId;

    @ManyToOne
    private Menu menu;

    @NotNull
    private String option;

    @NotNull
    private String content;

    @NotNull
    private int price;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    private String status;

    @OneToMany(mappedBy = "menuOption", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"menuOption"})
    private List<OrderMenuOption> menuOptionList = new ArrayList<>();

    public MenuOption addMenu(Menu menu){
        this.menu = menu;
        return this;
    }
}
