import React from 'react';
import { BoldText, Container } from '../components/style/common';
import Button from '../components/Button';
import { useNavigate } from 'react-router-dom';
import Order from './Order';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';

const Main = () => {
	const storeId = useSelector((state: RootState) => state.user.storeId);
	const navigate = useNavigate();
	const goLogin = () => {
		navigate('/login');
	};

	const goSignup = () => {
		navigate('/signup');
	};

	return (
		<Container>
			<BoldText>Foorending</BoldText>
			{storeId ? (
				<Order />
			) : (
				<Container>
					<Button
						label="로그인"
						onClick={goLogin}
						backgroundColor="#ffc46a"
						fontSize="16px"
						margin="10px"
						textColor="black"
						width={344}
						height={64}
						borderColor="rgb(240, 240, 240)"
					/>
					<Button
						label="회원가입"
						onClick={goSignup}
						backgroundColor="#ffc46a"
						fontSize="16px"
						margin="10px"
						textColor="black"
						width={344}
						height={64}
						borderColor="rgb(240, 240, 240)"
					/>
				</Container>
			)}
		</Container>
	);
};

export default Main;
