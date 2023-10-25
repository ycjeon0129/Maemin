import React, { useState, useEffect } from 'react';
import { Step1, Step2, Step3, Step4 } from '../components/signup';
import { checkId, signUp } from '../api/user';
import { useNavigate } from 'react-router';

const Signup = () => {
	const [id, setId] = useState<string>('');
	const [password, setPassword] = useState<string>('');
	const [confirmPassword, setConfirmPassword] = useState<string>('');
	const [phone, setPhone] = useState<string>('');
	const [verificationCode, setVerificationCode] = useState<string>('');
	const [username, setUsername] = useState<string>('');
	const [nickname, setNickname] = useState<string>('');
	const [gender, setGender] = useState<string>('');
	const [isPasswordMismatch, setIsPasswordMismatch] = useState<boolean>(false);
	const [selectedAgeGroup, setSelectedAgeGroup] = useState<number>(0);
	const [drawerOpen, setDrawerOpen] = useState<boolean>(false);
	const [timer, setTimer] = useState<number | null>(null);
	const [countdown, setCountdown] = useState<number>(180);
	const [step, setStep] = useState(1);
	const navigate = useNavigate();

	const nextStep = () => {
		if (step === 1) {
			if (!id || !password || !confirmPassword) {
				alert('아이디와 비밀번호를 모두 입력해주세요.');
				return;
			}
			if (isPasswordMismatch) {
				alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
				return;
			}
		}
		if (step === 2) {
			if (!phone || !verificationCode) {
				alert('전화번호와 인증번호를 모두 입력해주세요.');
				return;
			}
		}
		if (step === 3) {
			if (!nickname) {
				alert('닉네임을 입력해주세요.');
				return;
			}
		}
		if (step === 4) {
			if (!gender || !selectedAgeGroup) {
				alert('성별과 나이를 선택해주세요.');
				return;
			}
		}
		setStep(step + 1);
	};
	const prevStep = () => setStep(step - 1);
	const handleGenderSelect = (selectedGender: string) => {
		setGender(selectedGender);
	};
	useEffect(() => {
		let timerId: ReturnType<typeof setTimeout>; // 타입 수정
		if (timer !== null) {
			timerId = setInterval(() => {
				setCountdown((prevCountdown) => {
					if (prevCountdown === 0) {
						clearInterval(timerId);
						setTimer(null);
						return 180; // 초기값으로 설정
					}
					return prevCountdown - 1;
				});
			}, 1000);
		}
		return () => {
			clearInterval(timerId);
		};
	}, [timer]);

	const startTimer = (e: React.SyntheticEvent) => {
		e.preventDefault();
		setTimer(Date.now());
	};
	const displayTime = () => {
		const minutes = Math.floor(countdown / 60);
		const seconds = countdown % 60;
		return `${minutes}분 ${seconds}초`;
	};

	useEffect(() => {
		if (password && confirmPassword) {
			setIsPasswordMismatch(password !== confirmPassword);
		} else {
			setIsPasswordMismatch(false);
		}
	}, [password, confirmPassword]);

	const toggleDrawer = (e: React.SyntheticEvent) => {
		e.preventDefault();
		setDrawerOpen(!drawerOpen);
	};

	const handleAgeGroupSelect = (ageGroup: number) => {
		setSelectedAgeGroup(ageGroup);
		setDrawerOpen(!drawerOpen);
	};
	// 아이디 중복검사
	const checkIdDuplicate = async (data: { checkId: string }) => {
		try {
			if (!data.checkId) {
				return;
			}
			const response = await checkId(data.checkId);
			return response; // 서버 응답을 그대로 반환합니다.
		} catch (error) {
			alert('아이디 중복 검사 중 오류가 발생했습니다.');
			throw error;
		}
	};

	// 닉네임 중복검사
	const checkNicknameDuplicate = (e: React.SyntheticEvent) => {
		e.preventDefault();
		// 임시로 랜덤한 방법으로 중복을 확인합니다.
		// 실제로는 서버에 요청을 보내서 중복을 확인해야 합니다.
		const isDuplicate = Math.random() > 0.5;
		if (isDuplicate) {
			alert('이미 사용 중인 닉네임입니다.');
		} else {
			alert('사용 가능한 닉네임입니다.');
		}
	};

	const checkLogic = (e: React.SyntheticEvent) => {
		e.preventDefault();
		console.log('인증번호 확인 메서드');
	};

	const handleSubmit = async (e: React.SyntheticEvent) => {
		e.preventDefault();
		if (isPasswordMismatch) {
			alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
			return;
		}
		try {
			await signUp({
				loginId: id,
				loginPw: password,
				userName: username,
				nickName: nickname,
				phone: phone,
				sex: false, //-> False=남자 / True=여자
				age: selectedAgeGroup,
				role: 'ROLE_CUSTOMER', // ROLE_CUSTOMER or ROLE_OWNER
			});
			navigate('/login');
		} catch (e) {
			console.log(e);
		}
	};

	return (
		<form style={{ paddingLeft: '15px' }}>
			{step === 1 && (
				<Step1
					id={id}
					setId={setId}
					password={password}
					setPassword={setPassword}
					confirmPassword={confirmPassword}
					setConfirmPassword={setConfirmPassword}
					isPasswordMismatch={isPasswordMismatch}
					nextStep={nextStep}
					checkIdDuplicate={checkIdDuplicate}
				/>
			)}
			{step === 2 && (
				<Step2
					phone={phone}
					setPhone={setPhone}
					verificationCode={verificationCode}
					setVerificationCode={setVerificationCode}
					timer={timer}
					startTimer={startTimer}
					displayTime={displayTime}
					nextStep={nextStep}
					prevStep={prevStep}
					check={checkLogic}
				/>
			)}
			{step === 3 && (
				<Step3
					username={username}
					setUsername={setUsername}
					nickname={nickname}
					setNickname={setNickname}
					nextStep={nextStep}
					prevStep={prevStep}
					checkNicknameDuplicate={checkNicknameDuplicate}
				/>
			)}
			{step === 4 && (
				<Step4
					gender={gender}
					handleGenderSelect={handleGenderSelect}
					selectedAgeGroup={selectedAgeGroup}
					toggleDrawer={toggleDrawer}
					handleAgeGroupSelect={handleAgeGroupSelect}
					handleSubmit={handleSubmit}
					drawerOpen={drawerOpen}
				/>
			)}
		</form>
	);
};

export default Signup;
