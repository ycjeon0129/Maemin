// package com.tft.storeservice.order.db.entity;
//
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.ManyToOne;
//
// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Builder
// @Entity
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// @AllArgsConstructor
// @Data
// public class OrderMenuOption {
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Long orderMenuOptionId;
//
// 	private Long menuOptionId;
//
// 	@ManyToOne
// 	private OrderMenus orderMenu;
//
// 	public OrderMenuOption addOrderMenus(OrderMenus orderMenus){
// 		this.orderMenu = orderMenus;
// 		return this;
// 	}
// }
