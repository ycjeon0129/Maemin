import React from 'react';
import styled from 'styled-components';
import Carousel from './Carousel';
import { StoreName } from '../text';
import { useNavigate } from 'react-router';
import StoreImage from './StoreImage';

const SliderItem = styled.div`
	width: 100%;
	padding-left: 12px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	text-align: center;
	img {
		max-width: 100%;
		height: auto;
	}
`;

interface StoreData {
	storeId: number;
	name: string;
	pictureUrl: { storeImageId: number; storePicureUrl: string }[];
}

interface CarouselProps {
	trendword: string;
	storeData: StoreData[] | null;
}

function CarouselCard({ trendword, storeData }: CarouselProps) {
	const navigate = useNavigate();

	console.log('Received storeData:', storeData);

	if (!storeData || storeData.length === 0) {
		return (
			<Carousel keyword={trendword} storeDataLength={1}>
				<SliderItem>
					<StoreImage imageUrl="default_image_url" altDescription="default" />
					<StoreName>가게없음</StoreName>
				</SliderItem>
			</Carousel>
		);
	}

	return (
		<Carousel keyword={trendword} storeDataLength={storeData.length}>
			{storeData.map((store, index) => (
				<SliderItem
					key={index}
					onClick={() => {
						navigate(`/store-detail/${store.storeId}`);
					}}
				>
					<StoreImage
						imageUrl={store.pictureUrl[0]?.storePicureUrl}
						altDescription={store.name || 'Unnamed Store'}
					/>
					<StoreName>{store.name || 'Unnamed Store'}</StoreName>
				</SliderItem>
			))}
		</Carousel>
	);
}

export default CarouselCard;
