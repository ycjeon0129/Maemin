import API from './base';

export const getPublicKey = () => {
	return API.get('/pay-service/encrypt');
};
