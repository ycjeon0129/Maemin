import { PayloadAction, createSlice } from '@reduxjs/toolkit';

const initialState: secureState = {
	index: 0,
	publicKey: '0',
	validTime: '0',
};

export const secureSlice = createSlice({
	name: 'secure',
	initialState,
	reducers: {
		setKey: (state, action: PayloadAction<secureState>) => {
			state.index = action.payload.index;
			state.publicKey = action.payload.publicKey;
			state.validTime = action.payload.validTime;
		},
	},
});

export const secureActions = secureSlice.actions;
