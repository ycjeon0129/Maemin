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

interface Step1Props {
	id: string;
	setId: (value: string) => void;
	password: string;
	setPassword: (value: string) => void;
	confirmPassword: string;
	setConfirmPassword: (value: string) => void;
	isPasswordMismatch: boolean;
	checkIdDuplicate: (data: { checkId: string }) => Promise<{ message: string }>;
	nextStep: () => void;
}

const Step1 = ({
	id,
	setId,
	password,
	setPassword,
	confirmPassword,
	setConfirmPassword,
	isPasswordMismatch,
	checkIdDuplicate,
	nextStep,
}: Step1Props): JSX.Element => {
	const [isIdDuplicated, setIsIdDuplicated] = useState(true);

	const checkIdDuplicateHandler = async (e: React.SyntheticEvent) => {
		e.preventDefault();
		try {
			const response = await checkIdDuplicate({ checkId: id });
			if (response.message === 'SUCCESS') {
				alert('사용 가능한 아이디입니다.');
				setIsIdDuplicated(false);
			} else {
				alert('이미 사용 중인 아이디입니다.');
				setIsIdDuplicated(true);
			}
		} catch (error) {
			alert('오류가 발생했습니다.');
			console.error('ID 중복 검사 에러:', error);
		}
	};

	const handleNextClick = () => {
		if (isIdDuplicated) {
			alert('아이디 중복 확인을 해주세요.');
		} else {
			nextStep();
		}
	};

	return (
		<div>
			<Font>아이디,비밀번호입력</Font>
			<Input
				value={id}
				placeholder="ID"
				type="text"
				onChange={(value) => setId(value)}
				width={270}
				height={40}
				borderRadius="100px"
				border="white"
				margin="10px"
				paddingLeft="30px"
				readOnly={!isIdDuplicated} // 여기에 readOnly 추가
			/>
			<Button label="중복검사" fontSize="10px" width={57} height={26} onClick={checkIdDuplicateHandler} />
			<Input
				value={password}
				placeholder="PW"
				type="password"
				onChange={(value) => setPassword(value)}
				border={isPasswordMismatch ? '2px solid red' : '1px solid white'}
				width={270}
				height={40}
				borderRadius="100px"
				margin="10px"
				paddingLeft="30px"
			/>
			<Input
				value={confirmPassword}
				placeholder="PW 확인"
				type="password"
				onChange={(value) => setConfirmPassword(value)}
				border={isPasswordMismatch ? '2px solid red' : '1px solid white'}
				width={270}
				height={40}
				borderRadius="100px"
				margin="10px"
				paddingLeft="30px"
			/>
			{isPasswordMismatch && <div style={{ color: 'red' }}>비밀번호가 일치하지 않습니다.</div>}
			<Button
				label="다음 (1/4)"
				onClick={handleNextClick}
				borderRadius="20px"
				height={54}
				width={350}
				margintop="20px"
				textColor="white"
				backgroundColor="rgba(255, 182, 73, 1)"
				disabled={isPasswordMismatch || isIdDuplicated}
			/>
		</div>
	);
};

export default Step1;
