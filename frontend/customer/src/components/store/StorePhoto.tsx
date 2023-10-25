import React from 'react';
import styled from 'styled-components';
import StarRate from './StarRate';
import subStore from '../../assets/imgs/subs-store.png';
import { StoreImg } from '../../pages/Trend';

interface StorePhotoProps {
	name: string;
	pictureUrl: StoreImg[];
	rating: number;
}

const StorePhotoContainer = styled.div`
	position: relative;
	background-color: white;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	& > :last-child {
		margin: 0 30px;
		margin: 0 auto;
		padding: 10px 60px;
		border-radius: 10px;
		box-shadow: 5px 5px 20px rgba(0, 0, 0, 0.2);
	}
`;

const FixedSizeImage = styled.img`
	width: 236px;
	height: 156px;
	object-fit: fill;
	border-radius: 40px;
`;

const StoreInfoContainer = styled.div`
	text-align: center;
	display: flex;
	flex-direction: column;
	align-items: center;
`;

const RatingContainer = styled.div`
	display: flex;
	align-items: center;
	font-size: 24px;
	justify-content: center;
`;

const StoreName = styled.div`
	font-size: 24px;
	font-weight: 1000;
	margin: 10px;
	white-space: nowrap; //한줄로
`;

const StorePhoto = (props: StorePhotoProps) => {
	console.log(props);
	return (
		<StorePhotoContainer>
			<div>
				<FixedSizeImage
					src={props.pictureUrl.length != 0 ? props.pictureUrl[0].storePicureUrl : subStore}
					alt={props.name}
				/>
			</div>
			<StoreInfoContainer>
				<RatingContainer>
					<StarRate rating={props.rating} />
					<div>{props.rating}</div>
				</RatingContainer>
				<StoreName>{props.name}</StoreName>
			</StoreInfoContainer>
		</StorePhotoContainer>
	);
};

export default StorePhoto;
