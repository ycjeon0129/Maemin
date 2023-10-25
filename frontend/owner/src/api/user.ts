import API from './base';

export const signUp = (signUpData: SignupForm) =>
	API.post<User>('/user-service/users/join', signUpData).then((res) => console.log(res.data));

export const login = async (loginData: LoginForm) => {
	try {
		const res = await API.post<LoginRes>('/user-service/login', loginData);
		const tokenData = res.data.data;
		console.log(tokenData);

		localStorage.setItem('access_token', tokenData.accessToken);
		localStorage.setItem('expired_time', tokenData.expiredTime);

		return res.data.userInfo;
	} catch (error) {
		// 에러 핸들링을 여기에 추가할 수 있습니다.
		console.error('로그인에 실패했습니다.', error);
		alert('로그인에 실패했습니다. 다시 로그인해주세요.');
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
			location.href = '/owner';
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

export const checkId = async (checkId: string) => {
	try {
		const response = await API.post('/user-service/users/check', {
			checkId: checkId,
		});
		return response.data; // API 응답 구조에 따라 수정
	} catch (error) {
		console.error('아이디 중복 검사 실패:', error);
		throw error;
	}
};

export const sendSms = async (phoneNumber: string) => {
	try {
		const response = await API.post('/user-service/sms/send', {
			to: phoneNumber,
		});
		return response;
	} catch (error) {
		console.error('SMS 전송에 실패했습니다:', error);
		throw error;
	}
};

export const verifySms = async (phoneNumber: string, checkNum: string) => {
	try {
		const response = await API.post('/user-service/sms/auth', {
			to: phoneNumber,
			checkNum: checkNum,
		});
		return response;
	} catch (error) {
		console.error('SMS 확인에 실패했습니다:', error);
		throw error;
	}
};
