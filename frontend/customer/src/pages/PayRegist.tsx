import { useState, useRef } from 'react';
import Navigation from '../components/Navigation';
import Input from '../components/Input';
import { encrypt } from '../components/Encrypto';
import { useNavigate } from 'react-router';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';
import { userPayCardRegist } from '../api/pay';
import {
	PayRegistContainer,
	PayRegistContentBox,
	PayRegistTitleItem,
	PayRegistInputItem,
	PayRegistButtonItem,
} from '../components/style/payment';
import Modal from '../components/Modal';
const PayRegist = () => {
	const navigate = useNavigate();
	const index = useSelector((state: RootState) => state.secure.index);
	const [isOpen, setIsOpen] = useState(false);
	const [modalTitle, setModalTitle] = useState('');
	// const cardList = ['삼성카드', '현대카드', '롯데카드'];
	const cardNumFirstRef = useRef<HTMLInputElement>(null);
	const [cardNumFirst, setCardNumFirst] = useState('');
	const [cardNumSecond, setCardNumSecond] = useState('');
	const [cardNumThird, setCardNumThird] = useState('');
	const [cardNumFourth, setCardNumFourth] = useState('');
	const [cardPassword, setCardPassword] = useState('');
	const [cardCVC, setCardCVC] = useState('');
	const [cardYear, setCardYear] = useState('');
	const [cardMonth, setCardMonth] = useState('');
	// 검증 후 axios 요청
	const regist = () => {
		if (!checker(cardNumFirst, 4)) return false;
		if (!checker(cardNumSecond, 4)) return false;
		if (!checker(cardNumThird, 4)) return false;
		if (!checker(cardNumFourth, 4)) return false;
		if (!checker(cardPassword, 2)) return false;
		if (!checker(cardCVC, 3)) return false;
		if (!checker(cardMonth, 2)) {
			if (cardMonth.length == 1 || Number(cardMonth) > 13) return false;
		}
		if (!checker(cardYear, 2)) {
			if (cardYear.length == 1 || Number(cardYear) < 0 || Number(cardYear) > 99) return false;
		}

		const cardNumber = encrypt(cardNumFirst + '-' + cardNumSecond + '-' + cardNumThird + '-' + cardNumFourth);
		const cardExpireDate = encrypt(cardMonth + cardYear);
		const cvc = encrypt(cardCVC);
		const cardPw = encrypt(cardPassword);
		userPayCardRegist(cardNumber, cardExpireDate, cvc, cardPw, index)
			.then(() => {
				setIsOpen(true);
				setModalTitle('payRegist');
				setTimeout(() => {
					// 페이지 넘기는 로직

					navigate('/myPay');
				}, 3000);
			})
			.catch(() => {});
	};

	const checker = (value: string, length: number) => {
		if (value.length != length) return false;
		else return true;
	};
	return (
		<PayRegistContainer>
			{isOpen && <Modal isOpen={isOpen} title={modalTitle} />}
			<Navigation title={'간편결제 등록'}></Navigation>
			{/* <PayRegistContentBox>
				<PayRegistTitleItem>카드사 선택</PayRegistTitleItem>
				<Select list={cardList}></Select>
			</PayRegistContentBox> */}
			<PayRegistContentBox>
				<PayRegistTitleItem>카드 번호 입력</PayRegistTitleItem>
				<PayRegistInputItem>
					<Input
						type={'number'}
						max={'9999'}
						value={cardNumFirst}
						onChange={(value) => {
							if (Number(value) < 10000) setCardNumFirst(value);
						}}
						width="20%"
						borderRadius="5px"
						backgroundColor="rgba(0, 0, 0, 0.05)"
						border="none"
						padding="10px"
						ref={cardNumFirstRef}
					/>
					-
					<Input
						type={'password'}
						max={'9999'}
						value={cardNumSecond}
						onChange={(value) => {
							if (Number(value) < 10000) setCardNumSecond(value);
						}}
						width="20%"
						borderRadius="5px"
						backgroundColor="rgba(0, 0, 0, 0.05)"
						border="none"
						padding="10px"
					/>
					-
					<Input
						type={'password'}
						max={'9999'}
						value={cardNumThird}
						onChange={(value) => {
							if (Number(value) < 10000) setCardNumThird(value);
						}}
						width="20%"
						borderRadius="5px"
						backgroundColor="rgba(0, 0, 0, 0.05)"
						border="none"
						padding="10px"
					/>
					-
					<Input
						type={'number'}
						max={'9999'}
						value={cardNumFourth}
						onChange={(value) => {
							if (Number(value) < 10000) setCardNumFourth(value);
						}}
						width="20%"
						borderRadius="5px"
						backgroundColor="rgba(0, 0, 0, 0.05)"
						border="none"
						padding="10px"
					/>
				</PayRegistInputItem>
			</PayRegistContentBox>
			<PayRegistContentBox>
				<PayRegistTitleItem>비밀번호 입력</PayRegistTitleItem>
				<PayRegistInputItem>
					<Input
						type={'password'}
						value={cardPassword}
						onChange={(value) => {
							if (Number(value) < 99) setCardPassword(value);
						}}
						width="20%"
						borderRadius="5px"
						backgroundColor="rgba(0, 0, 0, 0.05)"
						border="none"
						padding="10px"
					/>
					**
				</PayRegistInputItem>
			</PayRegistContentBox>
			<PayRegistContentBox>
				<PayRegistTitleItem>CVC</PayRegistTitleItem>
				<PayRegistInputItem>
					<Input
						type={'password'}
						value={cardCVC}
						onChange={(value) => {
							if (Number(value) < 1000) setCardCVC(value);
						}}
						width="20%"
						borderRadius="5px"
						backgroundColor="rgba(0, 0, 0, 0.05)"
						border="none"
						padding="10px"
					/>
				</PayRegistInputItem>
			</PayRegistContentBox>
			<PayRegistContentBox>
				<PayRegistTitleItem>유효기간</PayRegistTitleItem>
				<PayRegistInputItem>
					<Input
						type={'number'}
						value={cardMonth}
						onChange={(value) => {
							if (Number(value) < 99 || value.length < 3) setCardMonth(value);
						}}
						width="20%"
						borderRadius="5px"
						backgroundColor="rgba(0, 0, 0, 0.05)"
						border="none"
						padding="10px"
						placeholder={'MM'}
					/>
					/
					<Input
						type={'number'}
						value={cardYear}
						onChange={(value) => {
							if (Number(value) < 100) setCardYear(value);
						}}
						width="20%"
						borderRadius="5px"
						backgroundColor="rgba(0, 0, 0, 0.05)"
						border="none"
						padding="10px"
						placeholder={'YY'}
					/>
				</PayRegistInputItem>
			</PayRegistContentBox>
			<PayRegistContentBox>
				<PayRegistButtonItem
					onClick={() => {
						regist();
					}}
				>
					페이 등록
				</PayRegistButtonItem>
			</PayRegistContentBox>
		</PayRegistContainer>
	);
};

export default PayRegist;
