// menuSlice.ts
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface MenuState {
	menuId: number;
	name: string;
	price: number;
	menuPictureUrl: string; // 여기에 menuPictureUrl 추가
}

const initialState: MenuState = {
	menuId: 0,
	name: '',
	price: 0,
	menuPictureUrl: '', // 초기 상태에도 menuPictureUrl 추가
};

const menuSlice = createSlice({
	name: 'menu',
	initialState,
	reducers: {
		setMenu: (state, action: PayloadAction<MenuState>) => {
			state.menuId = action.payload.menuId;
			state.name = action.payload.name;
			state.price = action.payload.price;
			state.menuPictureUrl = action.payload.menuPictureUrl; // setMenu 리듀서에도 menuPictureUrl 추가
		},
	},
});

export const { setMenu } = menuSlice.actions;

export default menuSlice.reducer;
