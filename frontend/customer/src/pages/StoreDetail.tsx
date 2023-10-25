import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
// import axios from 'axios';
import StorePhoto from '../components/store/StorePhoto';
import StoreMap from '../components/store/StoreMap';
import MenuList from '../components/store/MenuList';
import MedalIcon from '../assets/imgs/medal.svg';
import MenuBoardIcon from '../assets/imgs/menuBoard.svg';
import TrendKeyword from './../components/store/TrendKeyword';
import StoreInfo from './../components/store/StoreInfo';

import { useParams } from 'react-router-dom';
import { getStoreInfo } from '../api/store';
import { useDispatch, useSelector } from 'react-redux';
import { basketActions } from '../store/basketSlice';
import Navigation from '../components/Navigation';
import { RootState } from '../store/store';
import { StoreImg } from './Trend';

interface StoreDetailData {
	ownerId: number;
	name: string;
	category: number[];
	pictureUrl: StoreImg[];
	address: string;
	areaCode: number;
	phone: string;
	content: string;
	operationHours: string;
	closedDays: string;
	latitude: string;
	longitude: string;
	rating: number;
	dibsCount: number;
	reviewCount: number;
}
const StoreDetailContainer = styled.div`
	& > :nth-child(n + 2) {
		border-radius: 5px;
	}
`;
export interface MenuData {
	menuId: number;
	storeId: number;
	category: number[];
	name: string;
	price: number;
	menuPictureUrl: string;
	popularity: number;
}

const StoreDetail = () => {
	const userInfo = useSelector((state: RootState) => state.user);
	console.log(userInfo);
	const [storeData, setStoreData] = useState<StoreDetailData | null>(null);
	const [menuData, setMenuData] = useState<MenuData[]>([]);
	const params = useParams();
	const dispatch = useDispatch();
	storeData && dispatch(basketActions.setStore(storeData.name));
	params && dispatch(basketActions.setStoreId(Number(params.storeId)));
	useEffect(() => {
		const fetchData = async () => {
			try {
				getStoreInfo(Number(params.storeId)).then((response) => {
					setStoreData(response.data);
					console.log(storeData);
					setMenuData(response.data.menuList);
				});
			} catch (error) {
				console.error('There was an error fetching the data:', error);
			}
		};

		fetchData();
	}, []);

	const trendMenus = menuData.filter((menu) => menu.popularity === 1);
	const otherMenus = menuData.filter((menu) => menu.popularity === 0);

	return (
		<StoreDetailContainer>
			{storeData && <Navigation title={'가게'}></Navigation>}
			{storeData && (
				<StorePhoto name={storeData.name} pictureUrl={storeData?.pictureUrl} rating={storeData.rating} />
			)}
			{storeData && <TrendKeyword content={storeData.content} />}
			{storeData && <StoreInfo phone={storeData.phone} operationHours={storeData.operationHours} />}
			{storeData && (
				<StoreMap address={storeData.address} latitude={storeData.latitude} longitude={storeData.longitude} />
			)}
			<MenuList menu={trendMenus} title="인기 메뉴" iconSrc={MedalIcon} popularity={1} />
			<MenuList menu={otherMenus} title="다른 메뉴" iconSrc={MenuBoardIcon} popularity={0} />
		</StoreDetailContainer>
	);
};

export default StoreDetail;
