import { combineReducers, configureStore } from '@reduxjs/toolkit';
import { searchSlice } from './searchSlice';
import { basketSlice } from './basketSlice';
import { locationSlice } from './locationSlice';
import { secureSlice } from './secureSlice';
import menuReducer from './menuSlice';
import userReducer from './userSlice';
// import { persistReducer } from 'redux-persist';
// import storage from 'redux-persist/lib/storage';
// import persistStore from 'redux-persist/es/persistStore';

// const persistConfig = {
// 	key: 'root',
// 	storage: storage,
// 	whitelist: ['user'],
// };
const rootReducer = combineReducers({
	search: searchSlice.reducer,
	basket: basketSlice.reducer,
	menu: menuReducer,
	location: locationSlice.reducer,
	user: userReducer,
	secure: secureSlice.reducer,
});

// const persistedReducer = persistReducer(persistConfig, rootReducer);
const store = configureStore({
	reducer: rootReducer,
	// middleware: (getDefaultMiddleware) => getDefaultMiddleware({ serializableCheck: false }),
});
// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<typeof store.getState>;
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;
// export const useAppDispatch = () => useDispatch<AppDispatch>();
// export const persistor = persistStore(store);
export default store;
