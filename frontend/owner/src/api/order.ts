import API from './base';

export const getOrderList = (storeId: number) => {
	return API.get(`https://j9c208.p.ssafy.io/store-service/owner/order/info/${storeId}`);
};

export const updateOrderStatus = (newStatus: OrderStatus) =>
	API.post('/store-service/owner/order/change/status', newStatus);
