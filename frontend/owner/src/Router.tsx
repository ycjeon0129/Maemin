import React from 'react';
import { useRoutes } from 'react-router-dom';
import Main from './pages/Main';
import Login from './pages/Login';
import Signup from './pages/Signup';
import PageNotFound from './pages/PageNotFound';
import Order from './pages/Order';
import StoreManagement from './pages/StoreManagement';
import StoreInfo from './pages/StoreInfo';
import StoreMenu from './pages/StoreMenu';

/** Router */
export function MainRoutes() {
	return useRoutes([
		{
			path: '/',
			element: <Main />,
			children: [
				{
					path: 'main',
					element: <Main />, // 유저정보 없으면 로그인 회원가입 띄움
				},
			],
		},
		{
			path: '/login',
			element: <Login />,
		},
		{
			path: '/signup',
			element: <Signup />,
		},
		{
			path: '/order',
			element: <Order />,
		},
		{
			path: '/store-management',
			element: <StoreManagement />,
		},
		{
			path: '/store-info',
			element: <StoreInfo />,
		},
		{
			path: '/store-menu',
			element: <StoreMenu />,
		},
		{ path: '*', element: <PageNotFound /> },
	]);
}
