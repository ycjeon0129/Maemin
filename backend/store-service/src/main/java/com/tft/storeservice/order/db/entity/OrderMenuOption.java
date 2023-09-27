package com.tft.storeservice.order.db.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.tft.storeservice.menuoption.db.entity.MenuOption;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Embeddable
public class OrderMenuOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderMenuId;

	@ManyToOne
	Orders orders;

	@ElementCollection
	private List<Long> menuOptionId;

	public OrderMenuOption addOrders(Orders orders){
		this.orders = orders;
		return this;
	}
}
