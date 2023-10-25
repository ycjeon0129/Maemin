import React from 'react';
import { useRoutes } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import PageNotFound from './pages/PageNotFound';
import Trend from './pages/Trend';
import StoreDetail from './pages/StoreDetail';
import SearchHistory from './pages/SearchHistory';
import MenuDetail from './pages/MenuDetail';
import QrCodeReader from './components/QrScan';
import Basket from './pages/Basket';
import Payment from './pages/Payment';

import MyPay from './pages/MyPay';
import PayPassword from './pages/PayPassword';
import PayComplete from './pages/PayComplete';
import PayRegist from './pages/PayRegist';
import Log from './pages/Log';
import MyPage from './pages/MyPage';
/** Router */
export function MainRoutes() {
	return useRoutes([
		{
			path: '/',
			element: <Home />,
			children: [
				{
					path: 'home',
					element: <Home />,
				},
			],
		},
		{
			path: '/login',
			element: <Login />,
		},
		{
			path: '/mypage',
			element: <MyPage />,
		},
		{
			path: '/signup',
			element: <Signup />,
		},
		{
			path: '/trend',
			element: <Trend />,
		},
		{
			path: '/search',
			element: <SearchHistory />,
		},
		{
			path: '/qr',
			element: <QrCodeReader />,
		},
		{
			path: '/store-detail/:storeId',
			element: <StoreDetail />,
		},
		{
			path: '/menu-detail/:menuId',
			element: <MenuDetail />,
		},
		{
			path: '',
			children: [
				{
					path: '/basket',
					element: <Basket />,
				},
				{
					path: '/payment',
					element: <Payment />,
				},
				{
					path: '/myPay',
					element: <MyPay />,
				},
				{
					path: '/payPassword',
					element: <PayPassword />,
				},
				{
					path: '/payComplete',
					element: <PayComplete />,
				},
				{
					path: '/payRegist',
					element: <PayRegist />,
				},
			],
		},
		{
			path: '/log',
			element: <Log />,
		},

		{ path: '*', element: <PageNotFound /> },
	]);
}
