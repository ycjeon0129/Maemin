// package com.tft.storeservice.order.service;
//
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.tft.storeservice.menu.db.entity.Menu;
// import com.tft.storeservice.menu.db.repository.MenuRepository;
// import com.tft.storeservice.menuoption.db.entity.MenuOption;
// import com.tft.storeservice.menuoption.db.repository.MenuOptionRepository;
// import com.tft.storeservice.order.db.entity.OrderMenus;
// import com.tft.storeservice.order.db.entity.Orders;
// import com.tft.storeservice.order.db.repository.OrderMenusRepository;
// import com.tft.storeservice.order.db.repository.OrderRepository;
// import com.tft.storeservice.order.dto.request.OrderReq;
// import com.tft.storeservice.order.dto.response.OrderMenuRes;
// import com.tft.storeservice.order.dto.response.OrderOptionRes;
// import com.tft.storeservice.order.dto.response.OrderRes;
// import com.tft.storeservice.store.db.entity.Store;
// import com.tft.storeservice.store.db.repository.StoreRepository;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// @Service
// @RequiredArgsConstructor
// @Transactional
// @Slf4j
// public class OrderService {
//
// 	private final StoreRepository storeRepository;
// 	private final MenuRepository menuRepository;
// 	private final MenuOptionRepository menuOptionRepository;
// 	private final OrderRepository orderRepository;
// 	private final OrderMenusRepository orderMenusRepository;
//
// 	// public OrderRes getInfo(Long orderId){
// 	// 	OrderRes orderRes = new OrderRes(orderRepository.findById(orderId).orElseThrow());
// 	// 	int size = orderRes.getMenus().size();
// 	// }
//
// 	public OrderRes getInfo(Long orderId) {
// 		Orders orders = orderRepository.findById(orderId).orElseThrow();
// 		log.info("test " + orders.toString());
// 		OrderRes orderRes = new OrderRes();
// 		return orderRes;
//
// 	}
//
// 	public OrderRes register(OrderReq orderReq) {
// 		Orders orders = orderRepository.save(orderReq.toOrder(orderReq));
// 		OrderRes orderRes = new OrderRes(orders);
// 		int size = orderReq.getQuantity().size();
// 		int price = 0;
//
// 		for (int i = 0; i < size; i++) {
// 			OrderMenuRes orderMenuRes = new OrderMenuRes().toOrderMenu(orderReq, i);
// 			orderMenuRes.addMenu(getMenu(orderReq.getMenuId().get(i)));
// 			price += getMenu(orderReq.getMenuId().get(i)).getPrice();
//
// 			Long menuOptionId = orderReq.getMenuOptionIds().get(i);
// 			int quantity = orderReq.getQuantity().get(i);
// 			log.info("menuOptionId " + menuOptionId);
// 			OrderOptionRes orderOptionRes;
//
// 			if (menuOptionId != null) {
// 				OrderMenus orderMenus = OrderMenus.builder().build();
// 				orderMenus.addOrders(orders);
// 				orderMenus.addMenuOption(menuOptionId);
// 				log.info("optionId " + menuOptionId);
// 				orderMenusRepository.save(orderMenus);
//
// 				orderOptionRes = new OrderOptionRes().toOrderOption(getMenuOption(menuOptionId));
// 				price += orderOptionRes.getPrice();
// 				orderMenuRes.addOptions(orderOptionRes);
// 			}
//
// 			orderRes.addOrderMenuRes(orderMenuRes);
// 		}
// 		orderRes.addPrice(price);
// 		return orderRes;
// 	}
//
// 	public Store getStore(Long storeId) {
// 		return storeRepository.findById(storeId).orElseThrow();
// 	}
//
// 	public Menu getMenu(Long menuId) {
// 		return menuRepository.findById(menuId).orElseThrow();
// 	}
//
// 	public MenuOption getMenuOption(Long menuOptionId) {
// 		return menuOptionRepository.findById(menuOptionId).orElseThrow();
// 	}
// }
