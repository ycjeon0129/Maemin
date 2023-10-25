import axios from 'axios';
import { reissue } from './user';
import moment from 'moment';

const API = axios.create({
	baseURL: process.env.REACT_APP_BASE_URL,
	withCredentials: true,
});

/* access토큰있는데 만료되도 reissue가능해서 그대로 보냄. */
API.interceptors.request.use(
	async (config) => {
		const accessToken = localStorage.getItem('access_token');
		const expiredTime = localStorage.getItem('expired_time');
		if (accessToken) {
			/* 만료 체크 로직 */
			console.log(moment('2023-10-07 03:14:00'));
			console.log(moment(expiredTime).fromNow());

			if (moment(expiredTime).diff(moment()) <= 30) {
				const newAccessToken = await reissue();
				console.log('reissue됨 / 새토큰 : ', newAccessToken);
				config.headers!.Authorization = `Bearer ${newAccessToken}`;
			} else {
				console.log('기존 토큰 그대로 authorization에 담기 / 기존 토큰 : ', accessToken);
				config.headers!.Authorization = `Bearer ${accessToken}`;
			}
		}
		return config;
	},
	(error) => Promise.reject(error),
);

/** 새 access토큰받으면 갈아끼기 */
API.interceptors.response.use(
	(res) => {
		return res;
	},
	async (error) => {
		const originalRequest = error.config;

		// 요청 이후 401 - code가 expired이면 reissue요청
		if (error.response.status === 401 && error.response.data.code === 'EXPIRED') {
			try {
				const newAccessToken = await reissue();
				originalRequest.headers!.Authorization = `Bearer ${newAccessToken}`;
				return originalRequest;
			} catch (err) {
				alert('권한이 없습니다. 다시 로그인 해주세요');
			}
		} else {
			error.response && console.log(error.response);
		}
		//응답 200도 401도 아닌 경우 - 디버깅
		return Promise.reject(error);
	},
);

export default API;
