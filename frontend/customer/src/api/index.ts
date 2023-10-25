import axios from 'axios';

export const API = axios.create({
	baseURL: 'https://j9c208.p.ssafy.io/customer',
	headers: {
		'Content-Type': 'application/json',
	},
});
