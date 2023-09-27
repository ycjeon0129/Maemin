package com.tft.storeservice.order.db.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	private Long storeId;
	private Long userId;
	private int paymentMethod;
	private String requests;

	@ElementCollection
	private List<Long> menuId;

	@OneToMany(mappedBy = "orders", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"orders"})
	private List<OrderMenuOption> menuOptionId = new ArrayList<>();

	@ElementCollection
	private List<Integer> quantity = new ArrayList<>();

	private LocalDateTime createdTime;
}
