import { createSlice } from '@reduxjs/toolkit';

interface SearchState {
	focus: boolean;
}

const initialState: SearchState = {
	focus: false,
};

export const searchSlice = createSlice({
	name: 'search',
	initialState,
	reducers: {
		openSearch: (state) => {
			state.focus = true;
		},
		closeSearch: () => {
			return initialState;
		},
	},
});

export const { openSearch, closeSearch } = searchSlice.actions;
