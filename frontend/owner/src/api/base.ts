import axios from 'axios';
import { reissue } from './user';
import moment from 'moment';

const API = axios.create({
	baseURL: process.env.REACT_APP_BASE_URL,
	withCredentials: true,
});

const accessToken = localStorage.getItem('access_token');
const expiredTime = localStorage.getItem('expired_time');

/* access토큰있는데 만료되도 reissue가능해서 그대로 보냄. */
API.interceptors.request.use(
	async (config) => {
		if (accessToken) {
			/* 만료 체크 로직 */
			if (moment(expiredTime).diff(moment()) <= 30) {
				const newAccessToken = await reissue();
				config.headers!.Authorization = newAccessToken;
			} else {
				config.headers!.Authorization = `${accessToken}`;
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
				originalRequest.headers!.Authorization = `${newAccessToken}`;
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
