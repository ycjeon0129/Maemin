import React, { useState } from 'react';
import Input from '../../components/Input';
import Button from '../../components/Button';
import { PwWarningBox, StepColBox, StepContainer, StepRowBox } from '../style/user';

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
		<StepContainer>
			<StepRowBox>
				<Input
					value={id}
					placeholder="ID"
					type="text"
					onChange={setId}
					width={270}
					height={45}
					borderRadius="100px"
					border="white"
					margin="10px"
					paddingLeft="30px"
				/>
				<Button label="중복검사" fontSize="12px" width={65} height={30} onClick={checkIdDuplicateHandler} />
			</StepRowBox>
			<StepRowBox>
				<Input
					value={password}
					placeholder="PW"
					type="password"
					onChange={setPassword}
					border={isPasswordMismatch ? '2px solid red' : '1px solid white'}
					width={270}
					height={45}
					borderRadius="100px"
					margin="10px"
					paddingLeft="30px"
				/>
			</StepRowBox>
			<StepColBox>
				<Input
					value={confirmPassword}
					placeholder="PW 확인"
					type="password"
					onChange={setConfirmPassword}
					border={isPasswordMismatch ? '2px solid red' : '1px solid white'}
					width={270}
					height={45}
					borderRadius="100px"
					margin="10px"
					paddingLeft="30px"
				/>
				<PwWarningBox>
					{isPasswordMismatch && <div style={{ color: 'red' }}>비밀번호가 일치하지 않습니다.</div>}
				</PwWarningBox>
			</StepColBox>
			<Button
				label="다음 (1/4)"
				onClick={handleNextClick}
				borderRadius="20px"
				height={54}
				width={300}
				marginTop="20px"
				textColor="white"
				backgroundColor="rgba(255, 182, 73, 1)"
				disabled={isPasswordMismatch || isIdDuplicated}
			/>
		</StepContainer>
	);
};

export default Step1;
