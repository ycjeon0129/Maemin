import React, { useState } from 'react';
import Input from '../../components/Input';
import Button from '../../components/Button';
import { StepContainer } from '../style/user';
import styled from 'styled-components';

interface Step3Props {
	nickname: string;
	username: string;
	setNickname: (value: string) => void;
	setUsername: (value: string) => void;
	checkNicknameDuplicate: (e: React.SyntheticEvent) => void;
	prevStep: () => void;
	nextStep: () => void;
}

const ValidationError = styled.div`
	color: red;
	font-size: 16px;
	margin: 5px 0;
`;

const Step3 = ({ nickname, setNickname, username, setUsername, nextStep }: Step3Props): JSX.Element => {
	const [isNameValid, setIsNameValid] = useState(true);
	const [validationMessage, setValidationMessage] = useState('');

	const handleUsernameChange = (value: string) => {
		setUsername(value);
		if (value.length === 1) {
			setIsNameValid(false);
			setValidationMessage('이름이 너무 짧습니다.');
		} else {
			setIsNameValid(true);
			setValidationMessage('');
		}
	};

	const handleNextClick = () => {
		if (username.length === 1) {
			alert('이름이 너무 짧습니다.');
		} else {
			nextStep();
		}
	};

	return (
		<StepContainer>
			<Input
				value={nickname}
				placeholder="닉네임"
				type="text"
				onChange={setNickname}
				width={270}
				height={45}
				borderRadius="100px"
				border="white"
				margin="10px"
				paddingLeft="30px"
			/>

			<Input
				value={username}
				placeholder="이름"
				type="text"
				onChange={handleUsernameChange}
				width={270}
				height={45}
				borderRadius="100px"
				border={isNameValid ? '1px solid white' : '2px solid red'} // 여기서 변경
				margin="10px"
				paddingLeft="30px"
			/>

			{!isNameValid && <ValidationError>{validationMessage}</ValidationError>}
			<br />
			<Button
				label="다음 (3/4)"
				onClick={handleNextClick}
				borderRadius="20px"
				height={54}
				width={300}
				textColor="white"
				marginTop="20px"
				backgroundColor="rgba(255, 182, 73, 1)"
			/>
		</StepContainer>
	);
};

export default Step3;
