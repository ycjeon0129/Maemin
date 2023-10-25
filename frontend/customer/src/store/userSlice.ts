import { PayloadAction, createSlice } from '@reduxjs/toolkit';

export interface userState {
	loginId: string;
	userName: string;
	nickName: string;
	role?: string;
	pay?: boolean;
	payId?: number;
	payMethod: number;
}

const initialState: userState = {
	loginId: '',
	userName: '',
	nickName: '',
	payMethod: 0,
};

export const userSlice = createSlice({
	name: 'user',
	initialState,
	reducers: {
		setUser: (state, action: PayloadAction<userState>) => {
			state.loginId = action.payload.loginId;
			state.userName = action.payload.userName;
			state.nickName = action.payload.nickName;
			state.pay = action.payload.pay;
		},
		setPay: (state, action: PayloadAction<boolean>) => {
			state.pay = action.payload;
		},
		setPayId: (state, action: PayloadAction<number>) => {
			state.payId = action.payload;
		},
		setPayMethod: (state, action: PayloadAction<number>) => {
			state.payMethod = action.payload;
		},
	},
});

export const { setUser, setPay, setPayId, setPayMethod } = userSlice.actions;

export default userSlice.reducer;
