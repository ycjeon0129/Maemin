package com.tft.storeservice.menu.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.tft.storeservice.menuoption.db.entity.MenuOption;
import com.tft.storeservice.store.db.entity.Store;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Menu{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;

	@ManyToOne
	private Store store;

	@OneToMany(mappedBy = "menu", orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<MenuOption> menuList = new ArrayList<>();


	@NotNull
	private int category;

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
}
