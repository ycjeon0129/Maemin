import { useState, useEffect } from 'react';
import Navigation from '../components/Navigation';
import cardImg from '../assets/imgs/cardImg.png';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../store/store';
import Button from '../components/Button';
import { useNavigate } from 'react-router-dom';
import Carousel from '../components/Carousel/Carousel';
import cardPlus from '../assets/imgs/cardPlus.svg';
import { userPayCheck } from '../api/pay';
import Modal from '../components/Modal';
import {
	MyPayContainer,
	MyPayBox,
	MyPayDateBox,
	MyPayImg,
	MyPayInfoBox,
	MyPayInfoItem,
	MyPayInfoTitle,
	MyPayInfoValue,
	MyPayButtonBox,
} from '../components/style/payment';
import { setPayId } from '../store/userSlice';
const MyPay = () => {
	interface payProps {
		payId: number;
		company: string;
		basicInfo: string;
		nickname: string;
	}
	const dispatch = useDispatch();
	const [isOpen, setIsOpen] = useState(false);
	const today = new Date().toLocaleDateString().replaceAll('.', '').split(' ');
	today[1] = today[1].padStart(2, '0');
	const todayFormat = today.join('-');
	const price = useSelector((state: RootState) => state.basket.pickedMenuPrice);
	const userInfo = useSelector((state: RootState) => state.user);
	const basket = useSelector((state: RootState) => state.basket);
	const navigate = useNavigate();
	const [userPayList, setUserPayList] = useState<payProps[]>([]);

	const addCard = () => {
		navigate('/payRegist');
	};
	useEffect(() => {
		console.log(userInfo, ' userInfo');
		userPayCheck()
			.then((response) => {
				setUserPayList(response.data.payList);
				console.log(response.data.payList);
			})
			.catch((response) => console.log(response.data));
		if (userInfo.pay) {
			setIsOpen(false);
			// navigate('/payPassword');
		} else {
			setIsOpen(true);
			setTimeout(() => {
				setIsOpen(true);
				return navigate('/payPassword');
			}, 3000);
		}
	}, []);
	return (
		<MyPayContainer>
			{isOpen && <Modal isOpen={isOpen} title={'noPay'}></Modal>}
			<Navigation title={'페이 카드 선택'}></Navigation>
			<MyPayBox>
				<MyPayDateBox>{todayFormat}</MyPayDateBox>
				<Carousel dots={true} slideToShow={1} background={'black'} autoplay={false} loop={false}>
					{userPayList.length != 0 &&
						userPayList.map((item, index: number) => <MyPayImg src={cardImg} key={index}></MyPayImg>)}
					<MyPayImg
						src={cardPlus}
						onClick={() => {
							addCard();
						}}
					></MyPayImg>
				</Carousel>

				<MyPayInfoBox>
					<MyPayInfoItem>
						<MyPayInfoTitle>가게명</MyPayInfoTitle>
						<MyPayInfoValue>{basket.store}</MyPayInfoValue>
					</MyPayInfoItem>
					<MyPayInfoItem>
						<MyPayInfoTitle>결제 금액</MyPayInfoTitle>
						<MyPayInfoValue>{price}</MyPayInfoValue>
					</MyPayInfoItem>
				</MyPayInfoBox>
				<MyPayButtonBox>
					<Button
						label={'결제'}
						width={'100%'}
						padding={'10px'}
						borderRadius={'5px'}
						border={'none'}
						backgroundColor={'rgb(150, 150, 150)'}
						textColor={'white'}
						onClick={() => {
							navigate('/payPassword');
							dispatch(setPayId(userPayList[0].payId));
						}}
					></Button>
				</MyPayButtonBox>
			</MyPayBox>
		</MyPayContainer>
	);
};
export default MyPay;
