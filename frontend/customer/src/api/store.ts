import API from './base';

export const getAllStores = () => {
	return API.get('/store-service/customer/getAll')
		.then((res) => {
			console.log('getAllStores Response Data:', res.data);
			return res.data;
		})
		.catch((error) => {
			console.error('Error fetching all stores:', error);
			throw error;
		});
};

export const getStoresByCategory = (category: string) =>
	API.get(`/store-service/customer/stores?category=${category}`).then((res) => res.data);

export const getStoreList = (areaName: string) => {
	return API.post('/store-service/customer/find', {
		areaName,
	});
};

export const getStoreInfo = (storeId: number) => {
	return API.get(`/store-service/customer/stores/${storeId}`);
};
