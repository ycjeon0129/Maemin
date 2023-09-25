package com.tft.storeservice.store.db.entity;

import com.tft.storeservice.common.baseEntitty.BaseEntity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Store extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeId;

	@NotNull
	private int areaCode;

	@NotNull
	private String name;

	@NotNull
	private int category;

	@NotNull
	private String address;

	private String storeImageId;

	@NotNull
	private String phone;

	private String content;

	@NotNull
	private double rating;

	@NotNull
	private int dibsCount;

	@NotNull
	private int reviewCount;

	private String operationHours;
	private String closedDays;

	@NotNull
	private String status;

	@NotNull
	private Long ownerId;
}
