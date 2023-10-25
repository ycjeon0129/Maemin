import React, { useState } from 'react';
import Input from '../../components/Input';
import Button from '../../components/Button';
import styled from 'styled-components';

const Font = styled.div`
	font-size: 25px;
	margin-bottom: 20px;
	margin-left: 20px;
	margin-top: 20px;
	text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.5);
	font-weight: 700;
`;

const ValidationError = styled.div`
	color: red;
	font-size: 16px;
	margin: 5px 0;
`;

interface Step3Props {
	nickname: string;
	username: string;
	setNickname: (value: string) => void;
	setUsername: (value: string) => void;
	checkNicknameDuplicate: (e: React.SyntheticEvent) => void;
	prevStep: () => void;
	nextStep: () => void;
}

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
		<div>
			<Font>닉네임 입력</Font>
			<Input
				value={nickname}
				placeholder="닉네임"
				type="text"
				onChange={setNickname}
				width={270}
				height={40}
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
				height={40}
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
				width={350}
				textColor="white"
				margintop="20px"
				backgroundColor="rgba(255, 182, 73, 1)"
			/>
		</div>
	);
};

export default Step3;
