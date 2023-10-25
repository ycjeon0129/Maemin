/* eslint-disable @typescript-eslint/no-explicit-any */
import { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../../store/store';
import { getCurLoc } from '../../api/map';
import { locationActions } from '../../store/locationSlice';
import { getStoreList } from '../../api/store';
import { OverlayContainer, OverlayTitleBox } from '../CustomOverlay';

const { kakao } = window;

declare global {
	interface Window {
		// eslint-disable-next-line @typescript-eslint/no-explicit-any
		kakao: any;
	}
}

const MapContainer = () => {
	const location = useSelector((state: RootState) => state.location);
	const [curAddress, setCurAddress] = useState<string>('');
	const dispatch = useDispatch();

	const [markerList, setMarketList] = useState<any[]>([
		{
			latlng: new window.kakao.maps.LatLng(33.45023, 126.572965),
			title: 'test1',
		},
		{
			latlng: new window.kakao.maps.LatLng(33.455529, 126.561838),
			title: 'test2',
		},
	]);
	// 현 위치 세팅
	useEffect(() => {
		const lng = location.lng ? location.lng : 127.0495556;
		const lat = location.lat ? location.lat : 37.514575;
		dispatch(locationActions.setLocation(location));
		getCurLoc(lng, lat)
			.then((response) =>
				setCurAddress(response.data.documents[0].address_name.split(' ').splice(0, 2).join(' ')),
			)
			.catch((response) => console.error(response.data));
	}, []);

	// 현 위치 기준 가게 리스트 마커 세팅
	useEffect(() => {
		getStoreList(curAddress)
			.then((response) => {
				console.log(response.data);
				const getList: any[] = response.data.map((item: storeProps) => ({
					latlng: new window.kakao.maps.LatLng(item.latitude, item.longitude),
					title: item.name,
					storeId: item.storeId,
					content: item.content,
					phone: item.phone,
				}));
				setMarketList(getList);
				console.log(markerList);
			})
			.catch((response) => console.error(response.data));
	}, [curAddress]);

	const setMarkers = (map: any) => {
		markerList.forEach((obj) => {
			const marker = new kakao.maps.Marker({
				map: map,
				position: obj.latlng,
				title: obj.title,
				clickable: true,
				state: true,
			});
			// 마커에 표시할 인포윈도우를 생성합니다

			const url = `https://j9c208.p.ssafy.io/customer/store-detail/${obj.storeId}`;

			const overlay = new window.kakao.maps.CustomOverlay({
				content:
					`<div class="wrap" style="${OverlayContainer(obj.title.length)}" ` +
					`        <div class="title" style="${OverlayTitleBox}">` +
					`<a href=${url} style="text-decoration:none; color:black" >` +
					obj.title +
					'</a>' +
					'        </div>' +
					'</div>',
				// 인포윈도우에 표시할 내용
				removable: true,
				position: marker.getPosition(),
			});

			// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
			// 이벤트 리스너로는 클로저를 만들어 등록합니다
			// 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
			// kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
			window.kakao.maps.event.addListener(marker, 'click', makeClickListener(map, marker, overlay));
			// kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		});
	};

	// 인포윈도우 클릭하여 표시하는 함수
	function makeClickListener(map: any, marker: any, overlay: any) {
		return function () {
			!marker.state ? overlay.setMap(map) : overlay.setMap(null);
			marker.state = !marker.state;
		};
	}

	useEffect(() => {
		const container = document.getElementById('map');
		const mainPosition = new kakao.maps.LatLng(location.lat, location.lng);

		const options = {
			center: location
				? new kakao.maps.LatLng(location.lat, location.lng)
				: new kakao.maps.LatLng(37.420125, 127.126665),
			level: 5,
		};

		const mainMarker = new kakao.maps.Marker({
			position: mainPosition,
		});

		const map = new kakao.maps.Map(container, options);

		mainMarker.setMap(map); // 메인 위치 set
		setMarkers(map); // 마커 배열 set
	}, [location, markerList]);

	return (
		<div className="kakaomap">
			<div id="map" style={{ width: '390px', height: '300px' }} />
		</div>
	);
};

export default MapContainer;
