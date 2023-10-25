import API from './base';

export const getStoreInfo = (storeId: number) => {
	return API.get(`https://j9c208.p.ssafy.io/store-service/customer/stores/${storeId}`);
};

export const registMenu = (menu: MenuState) =>
	API.post('/store-service/owner/menu/register', menu)
		.then((res) => console.log(res.data))
		.catch((err) => console.log(err));
