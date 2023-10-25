import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Input from '../components/Input';
import Button from '../components/Button';
import Logo from '../assets/imgs/logo.jpg';
import { login } from '../api/user';
import { LogoWrapper, ButtonWrapper, InputWrapper, Container } from '../components/style/loginStyles';
import { useDispatch } from 'react-redux';
import { setUser } from '../store/userSlice';

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
		// 로그인 로직
		if (!id) {
			alert('id를 입력해주세요!');
			return;
		} else if (!password) {
			alert('password를 입력해주세요!');
			return;
		} else {
			const userInfo = await login({ loginId: id, loginPw: password });
			console.log('Logged in with ID:', id, 'Password:', password, 'userInfo', userInfo);
			userInfo && dispatch(setUser({ ...userInfo, loginId: id })); // 로그인 반환 데이터에서 유저 정보 redux에 저장
			// 로그인 성공여부에따라 redirect다르게
			localStorage.getItem('access_token') ? navigate('/') : alert('로그인에 실패하였습니다.');
		}
	};

	const handleSignup = () => {
		navigate('/signup');
	};

	return (
		<Container>
			<LogoWrapper>
				<img src={Logo} alt="Logo" width="250" height="250" />
			</LogoWrapper>
			<InputWrapper>
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
			</InputWrapper>
			<div>
				{/* 로그인 */}
				<ButtonWrapper>
					<Button
						label="로그인"
						onClick={handleLogin}
						backgroundColor="rgba(255, 182, 73, 1)"
						fontSize="16px"
						margin="10px"
						textColor="white"
						borderRadius="100px"
						width={380}
						height={64}
						borderColor="rgb(240, 240, 240)"
					/>
					{/* 회원가입 */}
					<Button
						label="회원가입"
						onClick={handleSignup}
						backgroundColor="rgba(255, 182, 73, 1)"
						fontSize="16px"
						margin="10px"
						textColor="white"
						borderRadius="100px"
						width={380}
						height={64}
						borderColor="rgb(240, 240, 240)"
					/>
				</ButtonWrapper>
			</div>
		</Container>
	);
};

export default Login;
