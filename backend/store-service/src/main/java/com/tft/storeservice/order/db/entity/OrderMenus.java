// package com.tft.storeservice.order.db.entity;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import javax.persistence.CascadeType;
// import javax.persistence.ElementCollection;
// import javax.persistence.Embeddable;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
//
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.tft.storeservice.menu.db.entity.Menu;
// import com.tft.storeservice.menuoption.db.entity.MenuOption;
// import com.tft.storeservice.order.dto.request.OrderReq;
//
// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Builder
// @Entity
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// @AllArgsConstructor
// @Getter
// public class OrderMenus {
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Long orderMenuId;
//
// 	@ManyToOne
// 	private Orders orders;
//
// 	private Long menuOptionId;
//
// 	private int quantity;
//
// 	@ManyToOne
// 	private Menu menu;
//
// 	public void addOrders(Orders orders){
// 		this.orders = orders;
// 	}
//
// 	public OrderMenus addMenuOption(OrderReq orderReq, int index){
// 		this.menuOptionId = orderReq.getMenuOptionIds().get(index);
//
// 		return this;
// 	}
// }
