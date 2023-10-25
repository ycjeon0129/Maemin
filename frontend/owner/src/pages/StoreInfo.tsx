import React, { useEffect } from 'react';
import { BoldText, Container, FlexBox, HeaderBox } from '../components/style/common';
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, Legend } from 'recharts';
import { CardContainer } from '../components/style/card';
import { logout } from '../api/user';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../store/store';
import { getStoreInfo } from '../api/store';
import { setStore } from '../store/storeSlice';
import Button from '../components/Button';
import { ContentBox, NumberBox, SmallTextBox, StoreInfoContainer, StyledImg, TextBox } from '../components/style/store';
import ClockIcon from '../assets/imgs/clock.svg';
import CallIcon from '../assets/imgs/call.svg';

const data = [
	{
		date: '8.12',
		'20대': 40,
		'30대': 24,
		amt: 24,
	},
	{
		date: '8.19',
		'20대': 30,
		'30대': 13,
		amt: 22,
	},
	{
		date: '8.26',
		'20대': 20,
		'30대': 66,
		amt: 22,
	},
	{
		date: '9.2',
		'20대': 27,
		'30대': 39,
		amt: 20,
	},
	{
		date: '9.9',
		'20대': 18,
		'30대': 48,
		amt: 21,
	},
	{
		date: '9.14',
		'20대': 23,
		'30대': 38,
		amt: 25,
	},
];

const StoreInfo = () => {
	const userInfo = useSelector((state: RootState) => state.user);
	const storeInfo = useSelector((state: RootState) => state.store);
	const dispatch = useDispatch();
	const noUser = () => {
		alert('로그인 정보가 존재하지 않습니다.');
		window.location.href = '/owner';
	};
	console.log(userInfo);
	useEffect(() => {
		!userInfo.nickName && noUser();

		// getStoreInfo(userInfo.storeId)
		getStoreInfo(userInfo.storeId!)
			.then((res) => {
				console.log(res.data);
				dispatch(setStore(res.data));
				console.log(storeInfo);
			})
			.catch((err) => console.log(err));
	}, []);
	return (
		<Container>
			<FlexBox dir="row">
				<HeaderBox>
					<BoldText>매장 정보</BoldText>

					{localStorage.getItem('access_token') && (
						<Button
							label="로그아웃"
							onClick={logout}
							backgroundColor="#ffe07a"
							fontSize="16px"
							margin="10px"
							textColor="white"
							borderRadius="100px"
							width={124}
							height={64}
							borderColor="rgb(240, 240, 240)"
						/>
					)}
				</HeaderBox>
			</FlexBox>
			<StoreInfoContainer>
				<BoldText>
					<TextBox>매장 이름 : {storeInfo.name}</TextBox>
				</BoldText>
				<ContentBox>
					<TextBox>매장 주소 : {storeInfo.address}</TextBox>
				</ContentBox>
				<ContentBox>
					<TextBox>카테고리 : {storeInfo.category}</TextBox>
				</ContentBox>
				<ContentBox>
					<TextBox>소개글 : {storeInfo.content}</TextBox>
				</ContentBox>
				<ContentBox>
					<StyledImg src={CallIcon} alt="시계아이콘" />
					<NumberBox>전화번호 : {storeInfo.phone}</NumberBox>
				</ContentBox>
				<ContentBox>
					<StyledImg src={ClockIcon} alt="시계아이콘" />
					<NumberBox>운영 시간 : {storeInfo.operationHours}</NumberBox>
				</ContentBox>
				<br />
				<ContentBox>
					<SmallTextBox>닉네임 : {userInfo.nickName}</SmallTextBox>
				</ContentBox>
				<ContentBox>
					<SmallTextBox>이름 : {userInfo.userName}</SmallTextBox>
				</ContentBox>
				<ContentBox>
					<SmallTextBox>매장아이디 : {userInfo.storeId}</SmallTextBox>
				</ContentBox>
			</StoreInfoContainer>

			<CardContainer width={800} height={300}>
				<LineChart width={750} height={250} data={data} margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
					<CartesianGrid strokeDasharray="3 3" />
					<XAxis dataKey="date" />
					<YAxis type="number" domain={[0, 80]} />
					<Tooltip />
					<Legend />
					<Line type="monotone" dataKey="20대" stroke="#8884d8" />
					<Line type="monotone" dataKey="30대" stroke="#82ca9d" />
				</LineChart>
			</CardContainer>
		</Container>
	);
};

export default StoreInfo;
