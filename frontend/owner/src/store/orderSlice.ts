import { createSlice, PayloadAction } from '@reduxjs/toolkit';

type MenuOption = {
	option: string;
	price: number;
};

interface OrderMenu {
	menuId: number;
	name: string;
	price: number;
	quantity: number;
	menuOptions?: MenuOption[];
}

interface OrderData {
	orderId: number;
	storeId: number;
	userId: number;
	paymentMethod: number;
	totalPrice: number;
	requests: string;
	createdDate: string;
	menus: OrderMenu[];
}

interface OrderState {
	orderList: OrderData[];
	price: string;
}

const initialState: OrderState = {
	orderList: [],
	price: '',
};

const orderSlice = createSlice({
	name: 'order',
	initialState,
	reducers: {
		setOrder: (state, action: PayloadAction<OrderState>) => {
			state.orderList = action.payload.orderList;
			state.price = action.payload.price;
		},
	},
});

export const { setOrder } = orderSlice.actions;

export default orderSlice.reducer;
