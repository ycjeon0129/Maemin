import React, { useEffect, useState } from 'react';
import { Container } from '../components/layout/common';
import Search from '../components/Search';
import MapContainer from '../components/map/MapContainer';
import CarouselCard from '../components/Carousel/CarouselCard';
import { getAllStores } from '../api/store';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';
export interface StoreImg {
	storeImageId: number;
	storePicureUrl: string;
}

interface Store {
	storeId: number;
	name: string;
	category: string;
	pictureUrl: StoreImg[];
}

const Trend = () => {
	const userInfo = useSelector((state: RootState) => state.user);
	console.log(userInfo);
	const [storeData, setStoreData] = useState<Record<string, Store[]>>({});

	useEffect(() => {
		const fetchData = async () => {
			try {
				const allStoresData: Store[] = await getAllStores();

				const fetchedData: Record<string, Store[]> = {};
				const categories = ['한식', '치킨', '일식', '양식', '분식', '카페', '기타'];

				categories.forEach((category) => {
					fetchedData[category] = allStoresData.filter((store) => store.category === category);
				});

				setStoreData(fetchedData);
			} catch (error) {
				console.error('Error fetching store data:', error);
			}
		};

		fetchData();
	}, []);
	return (
		<Container>
			<Search placeholder="배고프니까 일단 검색!!!" />
			<MapContainer />
			{['한식', '치킨', '일식', '양식', '분식', '카페', '기타'].map((category) => (
				<CarouselCard key={category} trendword={category} storeData={storeData[category]} />
			))}
		</Container>
	);
};

export default Trend;
