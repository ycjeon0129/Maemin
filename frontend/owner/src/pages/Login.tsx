import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Input from '../components/Input';
import Button from '../components/Button';
import { login } from '../api/user';
import { useDispatch } from 'react-redux';
import { setUser } from '../store/userSlice';
import { Container } from '../components/style/common';
import { LoginContainer } from '../components/style/user';

const Login = () => {
	const navigate = useNavigate();
	const dispatch = useDispatch();

	const [id, setId] = useState<string>('');
	const [password, setPassword] = useState<string>('');

	const handleIdChange = (value: string) => {
		setId(value);
	};

	const handlePasswordChange = (value: string) => {
		setPassword(value);
	};

	const handleLogin = async () => {
		if (!id || !password) {
			alert('아이디와 비밀번호를 모두 입력해주세요.');
			return;
		}

		// 로그인 로직
		const userInfo = await login({ loginId: id, loginPw: password }).catch(() => navigate('/login'));

		console.log('Logged in with ID:', id, 'Password:', password, 'userInfo', userInfo);
		userInfo && dispatch(setUser({ ...userInfo })); // 로그인 반환 데이터에서 유저 정보 redux에 저장

		// 로그인 성공여부에따라 redirect다르게
		localStorage.getItem('access_token') ? navigate('/store-info') : alert('다시 로그인 해주쉠');
	};

	const handleSignup = () => {
		// 회원가입 로직
		navigate('/signup');
	};

	return (
		<LoginContainer>
			<div>
				<Input
					value={id}
					placeholder="ID"
					type="text"
					onChange={handleIdChange}
					width={300}
					height={64}
					borderRadius="20px"
					border="white"
					margin="10px"
					paddingLeft="30px"
				/>
			</div>
			<div>
				<Input
					value={password}
					placeholder="PW"
					type="password"
					onChange={handlePasswordChange}
					width={300}
					height={64}
					borderRadius="20px"
					border="white"
					margin="10px"
					paddingLeft="30px"
				/>
			</div>
			<Container>
				{/* 로그인 */}
				<Button
					label="로그인"
					onClick={handleLogin}
					backgroundColor="rgba(255, 182, 73, 1)"
					fontSize="16px"
					margin="12px"
					textColor="white"
					borderRadius="100px"
					width={305}
					height={64}
					borderColor="rgb(240, 240, 240)"
				/>
				{/* 회원가입 */}
				<Button
					label="회원가입"
					onClick={handleSignup}
					backgroundColor="rgba(255, 182, 73, 1)"
					fontSize="16px"
					margin="12px"
					textColor="white"
					borderRadius="100px"
					width={305}
					height={64}
					borderColor="rgb(240, 240, 240)"
				/>
			</Container>
		</LoginContainer>
	);
};

export default Login;
