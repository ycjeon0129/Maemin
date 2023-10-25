import API from './base';

export const signUp = (signUpData: SignupForm) =>
	API.post<User>('/user-service/users/join', signUpData).then((res) => console.log(res.data));

export const login = async (loginData: LoginForm) => {
	try {
		const res = await API.post<LoginRes>('/user-service/login', loginData);
		const tokenData = res.data.data;
		console.log(res.data.userInfo);
		console.log(tokenData);

		localStorage.setItem('access_token', tokenData.accessToken);
		localStorage.setItem('expired_time', tokenData.expiredTime);

		return res.data.userInfo;
	} catch (error) {
		// 에러 핸들링을 여기에 추가할 수 있습니다.
		console.error('로그인에 실패했습니다.', error);
	}
};

export const logout = () =>
	API.post('/user-service/auth/logout', '', {
		headers: {
			'X-AUTH-TOKEN': `${localStorage.getItem('access_token')}`,
		},
	})
		.then((res) => {
			console.log(res.data);
			localStorage.removeItem('access_token');
			localStorage.removeItem('expired_time');
			location.href = '/customer';
		})
		.catch((err) => console.log(err));

export const reissue = async () => {
	try {
		const res = await API.get('/user-service/auth/reissue', {
			headers: {
				'X-AUTH-TOKEN': `${localStorage.getItem('access_token')}`,
			},
		});
		const tokenData = res.data.data;
		console.log(tokenData);

		localStorage.setItem('access_token', tokenData.accessToken);
		localStorage.setItem('expired_time', tokenData.expiredTime);

		return tokenData.accessToken;
	} catch (error) {
		console.error('로그인에 실패했습니다.', error);
	}
};

export const getMyLog = async () => {
	return API.get('/user-service/users/bills');
};
