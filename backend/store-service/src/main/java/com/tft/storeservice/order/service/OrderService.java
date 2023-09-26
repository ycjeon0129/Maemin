package com.tft.storeservice.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.menu.db.repository.MenuRepository;
import com.tft.storeservice.menuoption.db.entity.MenuOption;
import com.tft.storeservice.menuoption.db.repository.MenuOptionRepository;
import com.tft.storeservice.order.db.entity.OrderMenuOption;
import com.tft.storeservice.order.db.repository.OrderRepository;
import com.tft.storeservice.order.dto.request.OrderReq;
import com.tft.storeservice.order.dto.response.OrderMenuRes;
import com.tft.storeservice.order.dto.response.OrderOptionRes;
import com.tft.storeservice.order.dto.response.OrderRes;
import com.tft.storeservice.store.db.entity.Store;
import com.tft.storeservice.store.db.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

	private final StoreRepository storeRepository;
	private final MenuRepository menuRepository;
	private final MenuOptionRepository menuOptionRepository;
	private final OrderRepository orderRepository;

	// public OrderRes getInfo(Long orderId){
	// 	OrderRes orderRes = new OrderRes(orderRepository.findById(orderId).orElseThrow());
	// 	int size = orderRes.getMenus().size();
	// }

	public OrderRes register(OrderReq orderReq){
		OrderRes orderRes = new OrderRes(orderRepository.save(orderReq.toOrder(orderReq)));
		int size = orderReq.getQuantity().size();
		log.info("size " + size);
		log.info("size " + orderReq.getMenuOptionId());
		log.info("size " + orderReq.toOrder(orderReq).getQuantity().size());
		log.info("orderReq " + orderReq.toString());
		int price = 0;
		for(int i=0; i<size; i++){
			OrderMenuRes orderMenuRes = new OrderMenuRes().toOrderMenu(orderReq, i);
			orderMenuRes.addMenu(getMenu(orderReq.getMenuId().get(i)));
			log.info("menuId " + orderMenuRes.getMenuId().toString());
			log.info("price " + String.valueOf(orderMenuRes.getPrice()));
			price += getMenu(orderReq.getMenuId().get(i)).getPrice();
			log.info("plus price " + price);
			int length = orderReq.getMenuOptionId().get(i).getMenuOptionId().size();
			log.info(String.valueOf(length));
			for(int j=0; j<length; j++){
				Long menuOptionId = orderReq.getMenuOptionId().get(i).getMenuOptionId().get(j);
				log.info("menuOptionId " + menuOptionId);
				OrderOptionRes orderOptionRes = new OrderOptionRes().toOrderOption(getMenuOption(menuOptionId));
				price += orderOptionRes.getPrice();
				orderMenuRes.addOptions(orderOptionRes);
			}
			orderRes.addOrderMenuRes(orderMenuRes);
		}
		log.info("total price " + String.valueOf(price));
		orderRes.addPrice(price);
		return orderRes;
	}

	public Store getStore(Long storeId){
		return storeRepository.findById(storeId).orElseThrow();
	}

	public Menu getMenu(Long menuId){
		return menuRepository.findById(menuId).orElseThrow();
	}

	public MenuOption getMenuOption(Long menuOptionId){
		return menuOptionRepository.findById(menuOptionId).orElseThrow();
	}
}
