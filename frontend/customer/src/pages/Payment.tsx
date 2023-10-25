import { useState, useRef } from 'react';
import Navigation from '../components/Navigation';
import { RootState } from '../store/store';
import { useDispatch, useSelector } from 'react-redux';
import BasketTotalResult from '../components/basket/BasketTotalResult';
import Input from '../components/Input';
import Kakao from '../assets/imgs/payment_icon_yellow_medium.png';
import Naver from '../assets/imgs/NaverLogo.png';
import Foorending from '../assets/imgs/Foorending.png';
import BasketPayBtn from '../components/basket/BasketPayBtn';
import { useNavigate } from 'react-router-dom';
import { kakaoPayment } from '../api/pay';
import {
	PaymentContainer,
	PaymentTitleItem,
	PaymentMenuBox,
	PaymentMenuInfoBox,
	PaymentMenuInfoItem,
	PaymentMenuInfo,
	PaymentRequestBox,
	PaymentRequestContentItem,
	PaymentMethodBox,
	PaymentMethodContentBox,
	PaymentMethodContentItem,
	PaymentMethodContentImg,
} from '../components/style/payment';
import { basketActions } from '../store/basketSlice';
import { setPayMethod } from '../store/userSlice';
import Modal from '../components/Modal';

interface payMethod {
	src: string;
	name: string;
}
const PaySelect = () => {
	const menuList = useSelector((state: RootState) => state.basket.menuList);
	const myMenuList = menuList.filter((item) => item.menuPayerList.some((el) => el === '나') == true);
	const [selectedMethod, setSelectedMethod] = useState(-1);
	const userRequest = useRef<HTMLInputElement>(null);
	const navigate = useNavigate();
	const dispatch = useDispatch();
	// const storeInfo = useSelector((state: RootState) => state.basket);
	// storeInfo.storeId 하면 데이터 꺼내올 수 있음.
	const addRest = (price: number) => {
		return price.toLocaleString('ko-KR') + '원';
	};
	const user = useSelector((state: RootState) => state.user);
	const [isOpen, setIsOpen] = useState(false);
	const [modalTitle, setModalTitle] = useState('');
	const payMethodList = [
		{
			src: Foorending,
			name: '푸렌딩페이',
		},
		{
			src: Kakao,
			name: '카카오페이',
		},
		{
			src: Naver,
			name: '네이버페이',
		},
	];
	const clickArea = (index: number) => {
		setSelectedMethod(index);
	};
	const selectPaymentMethod = () => {
		if (selectedMethod == 1) {
			// 카카오페이 요청 코드
			//storeId, tableId, sessionId, requestId, store, amount
			const random = Math.floor(Math.random() * 8999999) + 1000000;
			kakaoPayment(1, 1, 1, random, '1', 1).then((response) => {
				console.log(response.data);
				window.open(response.data.next_redirect_mobile_url);
				// response.data.tid -> tid
				// response.data.next_redirect_mobile_url -> next_redirect_mobile_url
				// response.data.partner_order_id -> partner_order_id
				// window.location.href = next_redirect_mobile_url
			});
		} else if (selectedMethod == 2) {
			//네이버페이 요청 코드
		} else if (selectedMethod == 0) {
			navigate('/myPay');
		}
	};
	return (
		<PaymentContainer>
			{isOpen && <Modal isOpen={isOpen} title={modalTitle} />}
			<Navigation title={'결제'}></Navigation>
			<PaymentMenuBox>
				<PaymentTitleItem>내 결제항목</PaymentTitleItem>
				<PaymentMenuInfoBox>
					{myMenuList.map((item, index: number) => (
						<PaymentMenuInfoItem key={index}>
							<PaymentMenuInfo>{item.menuName}</PaymentMenuInfo>
							<PaymentMenuInfo>
								{addRest(
									Math.floor((item.menuPrice * item.menuCount) / item.menuPayerList.length / 10) * 10,
								)}
							</PaymentMenuInfo>
						</PaymentMenuInfoItem>
					))}
				</PaymentMenuInfoBox>
			</PaymentMenuBox>
			<PaymentRequestBox>
				<PaymentTitleItem>요청사항</PaymentTitleItem>
				<PaymentRequestContentItem>
					<Input
						width={'100%'}
						padding={'12px 15px'}
						border={'0'}
						fontSize={'8px'}
						backgroundColor={'rgba(0, 0, 0, 0.1)'}
						borderRadius={'5px'}
						onChange={() => {}}
						placeholder={'예) 맵지 않게 해주세요'}
						inputRef={userRequest}
					></Input>
				</PaymentRequestContentItem>
			</PaymentRequestBox>
			<BasketTotalResult></BasketTotalResult>
			<PaymentMethodBox>
				<PaymentTitleItem>결제 수단 선택</PaymentTitleItem>
				{payMethodList.map((item: payMethod, index: number) => (
					<PaymentMethodContentBox
						onClick={() => {
							clickArea(index);
							console.log(index);
						}}
						selected={index == selectedMethod}
					>
						<PaymentMethodContentImg src={item.src}></PaymentMethodContentImg>
						<PaymentMethodContentItem>{item.name}</PaymentMethodContentItem>
					</PaymentMethodContentBox>
				))}
			</PaymentMethodBox>
			<BasketPayBtn
				label={'결제하기'}
				onClick={() => {
					console.log(user.loginId);
					if (user.loginId !== '') {
						selectPaymentMethod();
						userRequest.current && dispatch(basketActions.setRequests(userRequest.current?.value));
						console.log(userRequest.current?.value);
						dispatch(setPayMethod(selectedMethod));
					} else {
						setIsOpen(true);
						setModalTitle('loginRequest');
						setTimeout(() => {
							// 페이지 넘기는 로직

							navigate('/login');
						}, 3000);
					}
				}}
				method={selectedMethod}
			></BasketPayBtn>
		</PaymentContainer>
	);
};
export default PaySelect;
