import React, { useEffect, useState } from 'react';
import { Container } from '../components/layout/common';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';
import { getMyLog, logout } from '../api/user';
import { MyOrderHistory, UserInfoBox } from '../components/style/mypage';
import { CardContainer } from '../components/Card';
import styled from 'styled-components';
import userIcon from '../assets/imgs/userIcon.png';
import Navigation from '../components/Navigation';
import Button from '../components/Button';
const UserImgItem = styled.div``;
const UserInfoItem = styled.div`
	display: flex;
	justify-content: center;
	align-items: flex-end;
	& > :first-child {
		display: flex;
		align-items: flex-start;
		font-size: 32px;
	}
	& > :last-child {
		display: flex;
		align-items: flex-end;
		font-size: 16px;
	}
`;
const LogoutBox = styled.div``;
const MyPage = () => {
	const [myLog, setMyLog] = useState([]);
	const userInfo = useSelector((state: RootState) => state.user);

	console.log(userInfo);

	useEffect(() => {
		const fetchData = async () => {
			try {
				await getMyLog().then((res) => {
					console.log(res.data);
					setMyLog(res.data);
				});
			} catch (error) {
				console.error('There was an error fetching the data:', error);
			}
		};

		fetchData();
	}, [userInfo]);

	return (
		<Container>
			<Navigation title={'마이페이지'} />
			<UserInfoBox>
				<UserImgItem>
					<img src={userIcon} />
				</UserImgItem>
				<UserInfoItem>
					<div>{userInfo.nickName}</div>
					<div>님</div>
				</UserInfoItem>
			</UserInfoBox>
			<MyOrderHistory>
				<UserInfoItem>
					<div>페이등록여부 : {userInfo.pay ? 'TFT 회원' : '페이 정보 없음'}</div>
				</UserInfoItem>
				내 결제 내역
				{myLog?.map((item: MyOrder, i) => {
					return (
						<CardContainer key={i}>
							<div>매장 이름 : {item.storeName}</div>
							<div>결제 수단 : {item.paymentMethod}</div>
							<div>결제 금액 : {item.totalPrice}</div>
							<p>주문 일시 : {item.createdDate.toString()}</p>
						</CardContainer>
					);
				})}
			</MyOrderHistory>
			<LogoutBox>
				<Button
					label={'로그아웃'}
					width={'300px'}
					padding={'20px'}
					margin={'20px 0'}
					borderRadius={'5px'}
					backgroundColor={'#ffb649'}
					textColor={'white'}
					fontWeight={'bold'}
					onClick={() => logout()}
				></Button>
			</LogoutBox>
		</Container>
	);
};

export default MyPage;
