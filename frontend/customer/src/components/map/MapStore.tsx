/* eslint-disable @typescript-eslint/no-explicit-any */
import { useEffect } from 'react';
const { kakao } = window;

declare global {
	interface Window {
		// eslint-disable-next-line @typescript-eslint/no-explicit-any
		kakao: any;
	}
}
interface LocProps {
	latitude: string;
	longitude: string;
}
const MapStore = ({ latitude, longitude }: LocProps) => {
	// 현 위치 세팅

	useEffect(() => {
		const container = document.getElementById('map');
		const mainPosition = new kakao.maps.LatLng(latitude, longitude);

		const options = {
			center: mainPosition,
			level: 5,
			draggable: false,
		};

		const mainMarker = new kakao.maps.Marker({
			position: mainPosition,
		});

		const map = new kakao.maps.Map(container, options);

		mainMarker.setMap(map);
	}, []);

	return (
		<div className="kakaomap">
			<div id="map" style={{ width: '390px', height: '300px' }} />
		</div>
	);
};

export default MapStore;
