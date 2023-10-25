import { useState, useRef, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { RootState, AppDispatch } from '../../store/store';
import { basketActions } from '../../store/basketSlice';
import {
	BasketDetailContainer,
	BasketDetailInfoBox,
	BasketDetailButtonItem,
	BasketDetailPriceItem,
	BasketDetailPayerBox,
	BasketDetailPayerItem,
	BasketDetailPayerNameItem,
	BasketDetailPayerListItem,
	BasketDetailPayerListTag,
	BasketDetailPayerList,
	BasketDetailResultItem,
	BasketDetailPayerResult,
} from '../style/basket';
interface MenuInfoProps {
	index: number;
}

function BasketMenuDetailPrice({ index }: MenuInfoProps) {
	const dispatch: AppDispatch = useDispatch();
	const [toggleInfo, setToggleInfo] = useState(false);
	const buttonRef = useRef<HTMLDivElement>(null);
	const menuList = useSelector((state: RootState) => state.basket.menuList);

	const menuIndex = menuList.findIndex((item) => {
		if (item.index == index) return true;
		else return false;
	});
	const menu = menuList[menuIndex];
	const menuPrice = menu.menuCount * menu.menuPrice;
	const menuPicker = menu.menuPicker;
	const payerList = menu.menuPayerList;
	// 가격 세자리마다 쉼표 추가 ex) 1,000원
	const addRest = (price: number) => {
		return price.toLocaleString('ko-KR') + '원';
	};
	// 결제 정보 열기
	const toggleDetailInfo = () => {
		setToggleInfo(!toggleInfo);
	};
	// 예상 결제 금액
	const predictPrice = () => {
		if (payerList.length > 0)
			return payerList.indexOf('나') !== -1
				? addRest(Math.floor(menuPrice / payerList.length / 10) * 10)
				: addRest(0);
		else return addRest(0);
	};
	useEffect(() => {
		dispatch(basketActions.setPickedPrice());
	}, [predictPrice]);
	return (
		<BasketDetailContainer>
			<BasketDetailInfoBox>
				<BasketDetailButtonItem
					className={toggleInfo ? 'rotate' : ''}
					ref={buttonRef}
					onClick={() => toggleDetailInfo()}
				>
					{'▼'}
				</BasketDetailButtonItem>
				<BasketDetailPriceItem>{addRest(menuPrice)}</BasketDetailPriceItem>
			</BasketDetailInfoBox>
			<BasketDetailPayerBox className={toggleInfo ? 'act' : ''} toggleInfo={toggleInfo}>
				<BasketDetailPayerItem>분할 결제</BasketDetailPayerItem>
				<BasketDetailPayerItem>
					<BasketDetailPayerNameItem>메뉴를 선택한 사람</BasketDetailPayerNameItem>
					<BasketDetailPayerListItem>
						<BasketDetailPayerListTag>ㄴ</BasketDetailPayerListTag>
						<BasketDetailPayerList>{menuPicker}</BasketDetailPayerList>
					</BasketDetailPayerListItem>
					<BasketDetailPayerNameItem>참여인원</BasketDetailPayerNameItem>
					<BasketDetailPayerListItem>
						<BasketDetailPayerListTag>ㄴ</BasketDetailPayerListTag>
						<BasketDetailPayerList>{payerList.map((item) => item + ' ')}</BasketDetailPayerList>
					</BasketDetailPayerListItem>
				</BasketDetailPayerItem>
				<BasketDetailResultItem>
					<BasketDetailPayerResult>예상 결제금액</BasketDetailPayerResult>
					<BasketDetailPayerResult>{predictPrice()}</BasketDetailPayerResult>
				</BasketDetailResultItem>
			</BasketDetailPayerBox>
		</BasketDetailContainer>
	);
}
export default BasketMenuDetailPrice;
