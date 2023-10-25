import { PayloadAction, createSlice } from '@reduxjs/toolkit';

export interface storeState {
	address: string;
	name: string;
	category: string;
	closeDays: string;
	content: string;
	operationHours: string;
	phone: string;
	pictureUrl?: PictureUrl[];
	menuList?: MenuState[];
}

interface PictureUrl {
	storeImageId: number;
	storePicureUrl: string;
}

const initialState: storeState = {
	address: '',
	name: '',
	category: '',
	closeDays: '',
	content: '',
	operationHours: '',
	phone: '',
	pictureUrl: [],
	menuList: [],
};

export const storeSlice = createSlice({
	name: 'store',
	initialState,
	reducers: {
		setStore: (state, action: PayloadAction<storeState>) => {
			state.address = action.payload.address;
			state.name = action.payload.name;
			state.category = action.payload.category;
			state.closeDays = action.payload.closeDays;
			state.content = action.payload.content;
			state.operationHours = action.payload.operationHours;
			state.phone = action.payload.phone;
			state.pictureUrl = action.payload.pictureUrl;
			state.menuList = action.payload.menuList;
		},
	},
});

export const { setStore } = storeSlice.actions;

export default storeSlice.reducer;
