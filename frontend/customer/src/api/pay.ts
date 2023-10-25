import API from './base';

// 자체 페이 가입(간편 비밀번호 등록)
export const userPayRegist = (payPw: string) => {
	return API.post('/pay-service/pay/user', { payPw });
};
// 자체 페이 조회
export const userPayCheck = () => {
	return API.get('/pay-service/pay');
};

// 자체 페이 카드 등록
export const userPayCardRegist = (
	cardNumber: string,
	cardExpireDate: string,
	cvc: string,
	cardPw: string,
	index: number,
) => {
	return API.post('/pay-service/pay', {
		cardNumber,
		cardExpireDate,
		cvc,
		cardPw,
		keyIndex: index,
	});
};
// 자체 페이 인증
export const userPayCardAuthenticate = (payPw: string) => {
	return API.post('/pay-service/pay/authentication', { payPw });
};

// 자체 페이 결제 요청 (PaymentReq)
export const userPayment = (requestId: number, store: string, amount: number, payId: number, code: string) => {
	console.log(requestId, store, amount, payId, code);
	return API.post('/payment-service/payment', { requestId, store, amount, payId, code });
};

// 카카오 페이 결제 요청
export const kakaoPayment = (
	storeId: number,
	tableId: number,
	sessionId: number,
	requestId: number,
	store: string,
	amount: number,
) => {
	return API.post('/payment-service/payment/kakao', { storeId, tableId, sessionId, requestId, store, amount });
};

// 주문하기
export const order = (
	storeId: number,
	requests: string,
	status: number,
	authCode: string,
	totalPrice: number,
	method: number,
	menus: menuListProps[],
	tableId: number,
) => {
	console.log(storeId, requests, status, authCode, totalPrice, method, menus, tableId);
	return API.post('/store-service/customer/order/register', {
		storeId,
		requests,
		status,
		authCode,
		totalPrice,
		method,
		menus,
		tableId,
	});
};
