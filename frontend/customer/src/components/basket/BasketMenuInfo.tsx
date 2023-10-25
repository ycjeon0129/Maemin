import { useState, useEffect, PropsWithChildren } from 'react';
import { useDispatch } from 'react-redux';
import type { AppDispatch } from '../../store/store';
import { basketActions } from '../../store/basketSlice';

import Button from '../../components/Button';
import BasketMenuDetailPrice from '../../components/basket/BasketMenuDetailPrice';
import {
	BasketMenuContainer,
	BasketMenuTitleBox,
	BasketMenuTitleItem,
	BasketMenuTitleBtn,
	BasketMenuInfoBox,
	BasketMenuImg,
	BasketMenuInfoItem,
	BasketMenuOption,
	BasketMenuName,
	BasketMenuCount,
	BasketMenuCountBtn,
	BasketMenuPriceItem,
} from '../style/basket';
interface MenuInfoProps {
	menuId: number;
	menuName: string;
	menuPrice: number;
	menuImg: string;
	menuCount: number;
	menuPayerList: string[];
	index: number;
}

const BasketMenuInfo = ({
	menuId,
	menuName,
	menuPrice,
	menuImg,
	menuCount,
	menuPayerList,
	index,
}: PropsWithChildren<MenuInfoProps>) => {
	const [count, setCount] = useState<number>(menuCount);
	const [totalPrice, setTotalPrice] = useState<number>(menuPrice * menuCount);
	const [payerIndex, setPayerIndex] = useState(menuPayerList.indexOf('나'));
	const payerCheck = payerIndex === -1;
	const dispatch: AppDispatch = useDispatch();
	// 수량 추가
	const addCount = () => {
		setCount(count + 1);
		dispatch(basketActions.addMenuCount(index));
		dispatch(basketActions.setTotalPrice(menuPrice));
	};
	// 수량 제거
	const subtractCount = () => {
		if (count == 1) {
			alert('메뉴 삭제?');
			return;
		}
		setCount(count - 1);
		dispatch(basketActions.subMenuCount(index));
		dispatch(basketActions.setTotalPrice(-menuPrice));
	};
	// 가격 세자리마다 쉼표 추가 ex) 1,000원
	const addRest = (price: number) => {
		return price.toLocaleString('ko-KR') + '원';
	};
	const deleteMenu = (index: number) => {
		console.log('전달 index' + index);
		dispatch(basketActions.deleteMenu(index));
	};
	const toggleParticipant = () => {
		console.log('asdf');
		if (payerIndex === -1) dispatch(basketActions.addParticipant(index));
		else dispatch(basketActions.deleteParticipant(index));
	};
	useEffect(() => {
		setTotalPrice(count * menuPrice);
	}, [count]);
	useEffect(() => {
		dispatch(basketActions.setTotalPrice(totalPrice));
	}, []);
	useEffect(() => {
		setPayerIndex(menuPayerList.indexOf('나'));
	}, []);
	return (
		<BasketMenuContainer>
			<BasketMenuTitleBox>
				<BasketMenuTitleItem>
					{menuName}({menuId})
				</BasketMenuTitleItem>
				<Button
					label={payerCheck ? '참여하기' : '참여중'}
					fontSize={'8px'}
					width={'60px'}
					padding={'5px'}
					borderRadius={'5px'}
					border={'solid'}
					fontWeight={'bold'}
					backgroundColor={payerCheck ? '' : 'rgba(123,160,255, 1)'}
					borderColor={payerCheck ? 'rgba(123,160,255, 1)' : 'white'}
					textColor={payerCheck ? 'rgba(123,160,255, 1)' : 'white'}
					onClick={() => {
						toggleParticipant();
					}}
				></Button>
				<BasketMenuTitleBtn onClick={() => deleteMenu(index)}>✖</BasketMenuTitleBtn>
			</BasketMenuTitleBox>
			<BasketMenuInfoBox>
				<BasketMenuInfoItem>
					<BasketMenuImg src={menuImg} alt="이미지 없음"></BasketMenuImg>
					<BasketMenuOption>
						<BasketMenuName>옵션(기본) : {addRest(menuPrice)}</BasketMenuName>
						<BasketMenuCount>
							<BasketMenuCountBtn onClick={() => subtractCount()}>-</BasketMenuCountBtn>
							<BasketMenuCountBtn>{count}</BasketMenuCountBtn>
							<BasketMenuCountBtn onClick={() => addCount()}>+</BasketMenuCountBtn>
						</BasketMenuCount>
					</BasketMenuOption>
				</BasketMenuInfoItem>
				<BasketMenuPriceItem>
					<BasketMenuDetailPrice index={index}></BasketMenuDetailPrice>
				</BasketMenuPriceItem>
			</BasketMenuInfoBox>
		</BasketMenuContainer>
	);
};

export default BasketMenuInfo;
