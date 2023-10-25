import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface sseState {
	flag: boolean;
}

const initialState: sseState = {
	flag: false,
};

const sseSlice = createSlice({
	name: 'sse',
	initialState,
	reducers: {
		setFlag: (state, action: PayloadAction<sseState>) => {
			state.flag = action.payload.flag;
		},
	},
});

export const { setFlag } = sseSlice.actions;

export default sseSlice.reducer;
