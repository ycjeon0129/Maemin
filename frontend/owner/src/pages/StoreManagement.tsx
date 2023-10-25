import React, { useEffect } from 'react';
import Card from '../components/Card';
import { BoldText, Container, FlexBox } from '../components/style/common';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../store/store';

const StoreManagement = () => {
	const storeId = useSelector((state: RootState) => state.user.storeId);
	const navigate = useNavigate();
	useEffect(() => {
		if (!(storeId! > 0)) {
			alert('등록된 매장이 없습니다');
			navigate('/');
		}
	});

	return (
		<Container>
			<FlexBox dir="row">
				<BoldText>가게 현황</BoldText>
			</FlexBox>
			<FlexBox dir="column">
				<FlexBox dir="row">
					<Card title="1번 테이블" content="음식 이름" titleSize="24px" width={200} />
					<Card title="2번 테이블" content="음식 이름" titleSize="24px" width={200} />
					<Card title="3번 테이블" content="음식 이름" titleSize="24px" width={200} />
				</FlexBox>
				<FlexBox dir="row">
					<Card title="4번 테이블" content="음식 이름" titleSize="24px" width={200} />
					<Card title="5번 테이블" content="음식 이름" titleSize="24px" width={200} />
					<Card title="6번 테이블" content="음식 이름" titleSize="24px" width={200} />
				</FlexBox>
			</FlexBox>
		</Container>
	);
};

export default StoreManagement;
