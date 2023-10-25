import React from 'react';
import styled from 'styled-components';
import CallIcon from '../../assets/imgs/call.svg';
import ClockIcon from '../../assets/imgs/clock.svg';

interface StoreInfoProps {
	phone: string;
	operationHours: string;
}
const StoreInfoContainer = styled.div`
	position: relative;
	background-color: white;
	display: flex;
	flex-direction: column;
	margin-top: 10px;
`;

const CallImg = styled.img`
	height: 25px;
	width: 25px;
`;
const ClockImg = styled.img`
	height: 25px;
	width: 25px;
`;

const CallNumber = styled.div`
	font-size: 24px;
	margin-left: 10px;
	margin-right: 10px;
`;

const StoreTime = styled.div`
	font-size: 24px;
	position: relative;
	margin-left: 10px;
	margin-right: 10px;
`;

const CallContainer = styled.div`
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	margin: 10px 20px;
`;
const CallBox = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
`;
const CallItem = styled.div`
	display: flex;
	align-items: center;
`;
const TimeContainer = styled.div`
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	margin: 10px 20px;
`;
const TimeBox = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
`;
const TimeItem = styled.div`
	display: flex;
	align-items: center;
`;
const StoreInfo = (props: StoreInfoProps) => {
	const { phone, operationHours } = props;

	console.log({ phone });
	console.log(operationHours);

	return (
		<StoreInfoContainer>
			<CallContainer>
				<CallBox>
					<CallItem>
						<CallImg src={CallIcon} alt="Call Icon" />
					</CallItem>
					<CallItem>
						<CallNumber>번호</CallNumber>
					</CallItem>
				</CallBox>
				<CallBox>{phone}</CallBox>
			</CallContainer>
			<TimeContainer>
				<TimeBox>
					<TimeItem>
						<ClockImg src={ClockIcon} alt="Clock Icon" />
					</TimeItem>
					<TimeItem>
						<StoreTime>영업시간</StoreTime>
					</TimeItem>
				</TimeBox>
				<TimeBox>{operationHours}</TimeBox>
			</TimeContainer>
		</StoreInfoContainer>
	);
};

export default StoreInfo;
