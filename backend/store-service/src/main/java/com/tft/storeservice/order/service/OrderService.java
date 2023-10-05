package com.tft.storeservice.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.menu.db.repository.MenuRepository;
import com.tft.storeservice.menuoption.db.entity.MenuOption;
import com.tft.storeservice.menuoption.db.repository.MenuOptionRepository;
import com.tft.storeservice.order.db.entity.OrderMenuOption;
import com.tft.storeservice.order.db.entity.OrderMenus;
import com.tft.storeservice.order.db.entity.Orders;
import com.tft.storeservice.order.db.repository.OrderMenuOptionRepository;
import com.tft.storeservice.order.db.repository.OrderMenusRepository;
import com.tft.storeservice.order.db.repository.OrderRepository;
import com.tft.storeservice.order.dto.request.OrderUpdateStatusReq;
import com.tft.storeservice.order.dto.request.OrderReq;
import com.tft.storeservice.order.dto.response.OrderRes;
// import com.tft.storeservice.sse.Service.SseService;
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
	private final OrderMenusRepository orderMenusRepository;
	private final OrderMenuOptionRepository orderMenuOptionRepository;
	// private final SseService sseService;

	public OrderRes getInfo(Long orderId) {
		return new OrderRes(orderRepository.findById(orderId).orElseThrow());
	}

	@Transactional
	public OrderRes changeStatus(OrderUpdateStatusReq orderUpdateStatusReq){
		Orders orders = orderRepository.findById(orderUpdateStatusReq.getOrderId()).orElseThrow();
		orders.updateStatus(orderUpdateStatusReq.getStatus());
		return new OrderRes(orders);
	}

	public List<OrderRes> getOrders(Long storeId){
		return orderRepository.findAllByStoreId(storeId).stream().map(OrderRes::new).collect(Collectors.toList());
	}

	@Transactional
	public Long register(OrderReq orderReq) {
		Orders orders = orderRepository.save(orderReq.toOrder(orderReq));
		int size = orderReq.getMenus().size();

		log.info("size : "+ size);

		for(int i=0; i<size; i++){
			OrderMenus orderMenus = OrderMenus.builder()
				.quantity(orderReq.getMenus().get(i).getQuantity())
				.build();
			orderMenus.addMenu(getMenu(orderReq.getMenus().get(i).getMenuId()));
			orderMenus.addOrders(orders);
			orderMenusRepository.save(orderMenus);

			int length = orderReq.getMenus().get(i).getMenuOptionId().size();
			for(int j=0; j<length; j++){
				OrderMenuOption orderMenuOption = OrderMenuOption.builder()
					.build();
				orderMenuOption.addMenuOption(getMenuOption(orderReq.getMenus().get(i).getMenuOptionId().get(j)));
				orderMenuOption.addOrderMenus(orderMenus);
				orderMenuOptionRepository.save(orderMenuOption);
			}
		}
		return orders.getOrderId();

		// return new OrderRes(orderRepository.findById(orders.getOrderId()).orElseThrow());		// int price = 0;
	}

	public void notify(Long orderId){
		Orders orders = orderRepository.findById(orderId).orElseThrow();
		OrderRes orderRes = new OrderRes(orders);
		// sseService.notify(orders.getStoreId(), orderRes);
	}

	public Store getStore(Long storeId) {
		return storeRepository.findById(storeId).orElseThrow();
	}

	public Menu getMenu(Long menuId) {
		return menuRepository.findById(menuId).orElseThrow();
	}

	public MenuOption getMenuOption(Long menuOptionId) {
		return menuOptionRepository.findById(menuOptionId).orElseThrow();
	}
}
