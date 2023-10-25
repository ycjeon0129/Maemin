import axios from 'axios';
const mapAPI = axios.create({
	baseURL: 'https://dapi.kakao.com/v2/local',
	headers: {
		'Content-Type': 'application/json;charset-UTF-8',
		Authorization: process.env.REACT_APP_KAKAO_MAP_REST_KEY,
	},
});

export const getCurLoc = (x: number, y: number) => {
	return mapAPI.get(`/geo/coord2regioncode?x=${x}&y=${y}`);
};
