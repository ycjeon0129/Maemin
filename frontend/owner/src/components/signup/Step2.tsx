import React, { useState } from 'react';
import Input from '../../components/Input';
import Button from '../../components/Button';
import { sendSms, verifySms } from '../../api/user';
import styled from 'styled-components';
import { StepContainer } from '../style/user';

interface Step2Props {
	phone: string;
	setPhone: (value: string) => void;
	verificationCode: string;
	setVerificationCode: (value: string) => void;
	startTimer: (e: React.SyntheticEvent) => void;
	displayTime: () => string;
	prevStep: () => void;
	nextStep: () => void;
	timer: number | null;
	check: (e: React.SyntheticEvent) => void;
}

const ValidationError = styled.div`
	color: red;
	font-size: 16px;
	margin: 5px 0;
	height: 20px;
`;

const InputButtonContainer = styled.div`
	display: flex;
	align-items: center;
`;

const Step2 = ({
	phone,
	setPhone,
	verificationCode,
	setVerificationCode,
	startTimer,
	displayTime,
	// prevStep,
	nextStep,
}: Step2Props): JSX.Element => {
	const [isPhoneValid, setIsPhoneValid] = useState(true);
	const [validationMessage, setValidationMessage] = useState('');
	const [isVerified, setIsVerified] = useState(false);

	const handlePhoneChange = (value: string) => {
		const onlyNums = value.replace(/[^\d]/g, '');
		let formattedPhone;
		if (onlyNums.length <= 3) {
			formattedPhone = onlyNums;
		} else if (onlyNums.length <= 7) {
			formattedPhone = `${onlyNums.slice(0, 3)}-${onlyNums.slice(3)}`;
		} else {
			formattedPhone = `${onlyNums.slice(0, 3)}-${onlyNums.slice(3, 7)}-${onlyNums.slice(7, 11)}`;
		}
		setPhone(formattedPhone);

		const regex = /^\d{3}-\d{4}-\d{4}$/;
		if (!regex.test(formattedPhone) || onlyNums.length !== 11) {
			setIsPhoneValid(false);
			setValidationMessage('전화번호를 제대로 입력해주세요.');
		} else {
			setIsPhoneValid(true);
			setValidationMessage('');
		}
	};

	const handleNextClick = () => {
		if (!isPhoneValid) {
			alert('전화번호를 제대로 입력해주세요.');
		} else {
			nextStep();
		}
	};

	const handleSendSmsClick = async (e: React.SyntheticEvent) => {
		e.preventDefault();
		try {
			const plainPhone = phone.replace(/-/g, '');
			const response = await sendSms(plainPhone);
			console.log(response.data);

			if (response.data.statusName === 'success') {
				alert('인증번호가 발송되었습니다.');
				startTimer(e); // 인증번호 발송 후 타이머 시작
			} else {
				alert('인증번호 발송에 실패했습니다.');
			}
		} catch (error) {
			alert('인증번호 발송에 실패했습니다.');
			console.error('인증번호 발송 중 에러:', error);
		}
	};

	const handleVerifySmsClick = async (e: React.SyntheticEvent) => {
		e.preventDefault();
		try {
			const plainPhone = phone.replace(/-/g, '');
			const response = await verifySms(plainPhone, verificationCode);
			if (response.data.message === 'SUCCESS') {
				console.log('인증번호 확인 성공');
				alert('인증번호 확인이 완료되었습니다.');
				setIsVerified(true); // 인증 성공 시 isVerified 상태를 true로 설정
				nextStep();
			} else {
				alert('인증번호 확인에 실패했습니다.');
				setIsVerified(false); // 인증 실패 시 isVerified 상태를 false로 설정
			}
		} catch (error) {
			console.error('인증번호 확인 과정에서 에러 발생:', error);
			alert('인증번호 확인에 실패했습니다.');
			setIsVerified(false); // 예외 발생 시 isVerified 상태를 false로 설정
		}
	};

	return (
		<StepContainer>
			<InputButtonContainer>
				<Input
					value={phone}
					placeholder="Phone"
					type="tel"
					onChange={handlePhoneChange}
					width={270}
					height={45}
					borderRadius="100px"
					border={isPhoneValid ? '1px solid white' : '2px solid red'}
					margin="10px"
					paddingLeft="30px"
				/>
				<Button label="인증번호 발송" fontSize="12px" width={95} height={30} onClick={handleSendSmsClick} />
			</InputButtonContainer>
			<ValidationError>{validationMessage}</ValidationError>
			<InputButtonContainer>
				<Input
					value={verificationCode}
					placeholder="인증번호"
					type="text"
					onChange={setVerificationCode}
					width={270}
					height={45}
					borderRadius="100px"
					border="white"
					margin="10px"
					paddingLeft="30px"
				/>
				<Button label="인증번호 확인" fontSize="12px" width={95} height={30} onClick={handleVerifySmsClick} />
			</InputButtonContainer>
			<div>남은 시간: {displayTime()}</div>
			<Button
				label="다음 (2/4)"
				onClick={handleNextClick}
				borderRadius="20px"
				height={54}
				width={350}
				textColor="white"
				marginTop="20px"
				backgroundColor="rgba(255, 182, 73, 1)"
				disabled={!isVerified}
			/>
		</StepContainer>
	);
};

export default Step2;
