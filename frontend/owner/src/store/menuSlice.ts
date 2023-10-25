import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface MenuState {
	name: string;
	price: string;
	menuPictureUrl: string; // 여기에 menuPictureUrl 추가
}

const initialState: MenuState = {
	name: '',
	price: '',
	menuPictureUrl: '', // 초기 상태에도 menuPictureUrl 추가
};

const menuSlice = createSlice({
	name: 'menu',
	initialState,
	reducers: {
		setMenu: (state, action: PayloadAction<MenuState>) => {
			state.name = action.payload.name;
			state.price = action.payload.price;
			state.menuPictureUrl = action.payload.menuPictureUrl; // setMenu 리듀서에도 menuPictureUrl 추가
		},
	},
});

export const { setMenu } = menuSlice.actions;

export default menuSlice.reducer;
