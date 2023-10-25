import React, { useEffect } from 'react';
import styled from 'styled-components';
import Card from '../components/Card';
import { Container } from '../components/layout/common';
import { ReactComponent as ReservationIcon } from '../assets/imgs/reservation.svg';
import { ReactComponent as CartIcon } from '../assets/imgs/cart.svg';
import { ReactComponent as QrIcon } from '../assets/imgs/qrImg.svg';
import Search from '../components/Search';
import { useDispatch, useSelector } from 'react-redux';
import useGeolocation from '../hooks/useGeolocation';
import { locationActions } from '../store/locationSlice';
import { RootState } from '../store/store';
import { getPublicKey } from '../api/crypto';
import { useLocation, useNavigate } from 'react-router-dom';
import moment from 'moment';
import homecategory from '../assets/imgs/homecategory.png';
import { secureActions } from '../store/secureSlice';
import { order } from '../api/pay';
import { basketActions } from '../store/basketSlice';
type directionType = {
	dir: string;
};

const HomeBox = styled.div<directionType>`
	display: flex;
	flex-direction: ${(props) => (props.dir === 'row' ? 'row' : 'column')};
`;

const HomeCategoryIcon = styled.img`
	width: 90px;
	height: 90px;
`;

const Home = () => {
	const dispatch = useDispatch();
	const navigate = useNavigate();
	const basket = useSelector((state: RootState) => state.basket);
	const menuCount = useSelector((state: RootState) => state.basket.menuList.length);
	const secure = useSelector((state: RootState) => state.secure);
	const urlLocation = useLocation();
	const urlLoc = urlLocation.search;

	useEffect(() => {
		if (urlLoc != '') {
			const code = urlLoc.split('=')[1];
			const menus: menuListProps[] = basket.menuList.map((item) => {
				return { menuId: item.menuId, menuOptionId: [], quantity: item.menuCount };
			});
			order(basket.storeId, basket.requests, 1, code, basket.totalPrice, 2, menus, 1).then(() => {
				dispatch(basketActions.initBasket());
				navigate('/home');
			});
		}
	}, []);
	const location = useGeolocation();
	console.log(basket);
	const locationInfo = location.coordinates;
	const myLocation = {
		lat: locationInfo?.lat,
		lng: locationInfo?.lng,
	};
	useEffect(() => {
		dispatch(locationActions.setLocation(myLocation));
	}, [location]);

	// 초기 암호키 상태 세팅
	const initSecureState = ({ index, publicKey, validTime }: secureState) => {
		dispatch(secureActions.setKey({ index, publicKey, validTime }));
	};
	useEffect(() => {
		const curValidTime = secure.validTime;
		const diff = moment.duration(moment(curValidTime).diff(moment())).asSeconds();
		if (diff <= 90) {
			getPublicKey().then((response) => {
				const index = response.data.key;
				const publicKey = response.data.value;
				const validTime = response.data.validTime;
				initSecureState({ index, publicKey, validTime });
			});
		}
	}, []);

	return (
		<Container>
			<Search placeholder="배고프니까 일단 검색!!!" />
			<HomeBox dir="column">
				<Card
					title="카테고리"
					content="카테고리별 가게 보러가기"
					height={160}
					icon={(props) => <HomeCategoryIcon src={homecategory} {...props} />}
					iconSize={90}
					titleSize="24px"
					url={'trend'}
				/>
				<Card
					title="예약하기"
					content="원하는 매장에 예약해보세요"
					height={160}
					icon={(props) => <ReservationIcon {...props} />}
					iconSize={60}
					titleSize="24px"
				/>
			</HomeBox>
			<HomeBox dir="row">
				<Card
					title="내 주문 현황"
					content="현재 함께 주문확인"
					width={160}
					height={160}
					icon={(props) => <CartIcon {...props} />}
					iconSize={40}
					url={'basket'}
					count={menuCount}
				/>
				<Card
					title="QR 촬영"
					content="QR로 주문을 시작해보세요!"
					width={160}
					height={160}
					icon={(props) => <QrIcon {...props} />}
					iconSize={40}
					url={'qr'}
				/>
			</HomeBox>
		</Container>
	);
};

export default Home;
