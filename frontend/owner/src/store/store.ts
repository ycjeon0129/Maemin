import { combineReducers, configureStore } from '@reduxjs/toolkit';
import menuReducer from './menuSlice';
import userReducer from './userSlice';
import orderReducer from './orderSlice';
import storeReducer from './storeSlice';
import sseReducer from './sseSlice';

const rootReducer = combineReducers({
	menu: menuReducer,
	user: userReducer,
	order: orderReducer,
	store: storeReducer,
	sse: sseReducer,
});

const store = configureStore({
	reducer: rootReducer,
});

// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<typeof store.getState>;
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;
// export const useAppDispatch = () => useDispatch<AppDispatch>();
export default store;
