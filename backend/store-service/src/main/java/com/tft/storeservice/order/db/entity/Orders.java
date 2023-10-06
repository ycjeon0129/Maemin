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
import com.tft.storeservice.order.dto.request.OrderReq;

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
	private String requests;
	private int totalPrice;
	private int status;
	private Long tableId;

	@OneToMany(mappedBy = "orders", orphanRemoval = true, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"orders"})
	private List<OrderMenus> menuOptionId = new ArrayList<>();

	private LocalDateTime createdTime;

	public void addPrice(int price){
		this.totalPrice = price;
	}

	public void updateStatus(int status){
		this.status = status;
	}

}
