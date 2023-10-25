import { PayloadAction, createSlice } from '@reduxjs/toolkit';

export interface userState {
	nickName: string;
	userName: string;
	role?: string;
	storeId?: number;
}

const initialState: userState = {
	nickName: '',
	userName: '',
	storeId: 0,
};

export const userSlice = createSlice({
	name: 'user',
	initialState,
	reducers: {
		setUser: (state, action: PayloadAction<userState>) => {
			state.userName = action.payload.userName;
			state.nickName = action.payload.nickName;
			action.payload.storeId ? (state.storeId = action.payload.storeId) : (state.storeId = 0);
		},
	},
});

export const { setUser } = userSlice.actions;

export default userSlice.reducer;
