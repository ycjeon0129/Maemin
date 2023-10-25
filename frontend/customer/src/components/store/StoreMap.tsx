import React from 'react';
import styled from 'styled-components';
import StoreMapIcon from '../../assets/imgs/storemap.svg';
import MapStore from '../../components/map/MapStore';
interface StoreMapProps {
	latitude: string;
	longitude: string;
	address: string;
}

const StoreMapContainer = styled.div`
	position: relative;
	background-color: white;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	margin-top: 10px;
`;

const ContentContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	margin: 10px 20px;
`;

const NavidName = styled.div`
	font-size: 24px;
	position: relative;
	margin-left: 10px;
`;

const NaviInfor = styled.div`
	text-align: center;
	color: rgba(0, 0, 0, 0.5);
	margin-bottom: 5px;
`;

const StoreMap = (props: StoreMapProps) => {
	console.log(props.latitude, props.longitude);
	return (
		<div>
			<StoreMapContainer>
				<ContentContainer>
					<img src={StoreMapIcon} alt="Tag" />
					<NavidName>위치정보</NavidName>
				</ContentContainer>
				<MapStore latitude={props.latitude} longitude={props.longitude}></MapStore>
				<NaviInfor>{props.address}</NaviInfor>
			</StoreMapContainer>
		</div>
	);
};

export default StoreMap;
