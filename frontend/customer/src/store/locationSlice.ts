import { PayloadAction, createSlice } from '@reduxjs/toolkit';

const initialState: locationState = {
	lat: 0,
	lng: 0,
};

export const locationSlice = createSlice({
	name: 'location',
	initialState,
	reducers: {
		setLocation: (state, action: PayloadAction<locationState>) => {
			state.lat = action.payload.lat;
			state.lng = action.payload.lng;
		},
	},
});

export const locationActions = locationSlice.actions;
export default locationSlice.reducer;
