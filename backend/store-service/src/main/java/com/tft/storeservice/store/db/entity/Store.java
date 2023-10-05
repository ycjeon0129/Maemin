package com.tft.storeservice.store.db.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tft.storeservice.area.db.entity.Area;
import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.storeImage.db.entity.StoreImage;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Store{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeId;

	@ManyToOne
	private Area area;

	@Getter
	private String name;

	private String category;

	private String address;

	@OneToMany(mappedBy = "store", orphanRemoval = true, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"store"})
	private List<Menu> menuList = new ArrayList<>();

	@OneToMany(mappedBy = "store", orphanRemoval = true, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"store"})
	private List<StoreImage> storeImageList = new ArrayList<>();

	private String phone;

	private String content;

	// @NotNull
	// private double rating;

	// @OneToMany(mappedBy = "store", orphanRemoval = true, cascade = CascadeType.REMOVE)
	// private List<Dibs> dibsList = new ArrayList<>();

	// @NotNull
	// private int reviewCount;

	private String operationHours;
	private String closedDays;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdDate;

	private String status;

	private Long ownerId;
	
	// 위도
	private String latitude;
	// 경도
	private String longitude;

	public Store addArea(Area area) {
		this.area = area;
		return this;
	}
}
