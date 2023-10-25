import React, { useState, useEffect } from 'react';

import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../store/store';
import styled from 'styled-components';
import FoodCount from '../components/menu/FoodCount';
import Button from '../components/Button';
import ConfirmModal from '../components/ConfirmModal';
import { basketActions } from '../store/basketSlice';
import Navigation from '../components/Navigation';

const FoodPhoto = styled.div`
	width: 390px;
	height: 304px;
	position: relative;
`;

const FoodImage = styled.img`
	width: 100%;
	height: 100%;
	object-fit: cover;
	top: 0;
	left: 0;
`;

const FoodName = styled.div`
	font-size: 24px;
	position: relative;
	margin: 10px;
	padding: 10px 0;
`;

const PriceName = styled.div`
	font-size: 16px;
	position: relative;
	margin-left: 10px;
	padding: 10px;
	font-weight: bold;
`;

const FoodPrice = styled.div`
	font-size: 15px;
	position: relative;
	color: rgba(0, 0, 0, 0.5);
`;

const ButtonWrapper = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 20px;
`;

const FoodWrapper = styled.div`
	background-color: white;
	margin-bottom: 20px;
`;
const FoodPriceInfo = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 0 10px;
`;
const MenuDetail = () => {
	const selectedMenu = useSelector((state: RootState) => state.menu);
	const menuList = useSelector((state: RootState) => state.basket.menuList);
	const [quantity, setQuantity] = useState(1);
	const [totalPrice, setTotalPrice] = useState(0);
	const [isModalOpen, setIsModalOpen] = useState(false);
	const dispatch = useDispatch();

	const addMenuList = () => {
		const lastIndex = menuList.length !== 0 ? menuList[menuList.length - 1].index + 1 : 0;
		const selectMenu = {
			menuId: selectedMenu.menuId,
			menuName: selectedMenu.name,
			menuPrice: selectedMenu.price,
			menuCount: quantity,
			menuImg: selectedMenu.menuPictureUrl,
			menuPicker: '나',
			menuPayerList: ['나'],
			index: lastIndex,
		};
		dispatch(basketActions.addMenu(selectMenu));
		setIsModalOpen(true);
	};

	const addRest = (price: number) => {
		return price.toLocaleString('ko-KR') + '원';
	};

	const handleConfirm = () => {
		console.log('확인 후');
	};

	useEffect(() => {
		if (selectedMenu && selectedMenu.price) {
			setTotalPrice(quantity * selectedMenu.price);
		}
	}, [quantity, selectedMenu]);

	return (
		<div>
			<Navigation></Navigation>
			<FoodPhoto>
				<FoodImage src={selectedMenu.menuPictureUrl} alt={selectedMenu.name} />
			</FoodPhoto>
			<FoodWrapper>
				<FoodName>{selectedMenu.name}</FoodName>
				<FoodPriceInfo>
					<PriceName>가격</PriceName>
					<FoodPrice>{addRest(selectedMenu.price)}</FoodPrice>
				</FoodPriceInfo>
			</FoodWrapper>
			<FoodCount quantity={quantity} setQuantity={setQuantity} />
			<ButtonWrapper>
				<Button
					label={`${totalPrice}원 담기`}
					backgroundColor="rgba(255, 182, 73, 1)"
					fontSize="16px"
					margin="10px"
					textColor="white"
					borderRadius="100px"
					width={344}
					height={64}
					borderColor="rgb(240, 240, 240)"
					onClick={() => addMenuList()}
				/>
			</ButtonWrapper>
			{isModalOpen && (
				<ConfirmModal
					message="장바구니에 담겼습니다."
					onConfirm={handleConfirm}
					onCancel={() => setIsModalOpen(false)}
				/>
			)}
		</div>
	);
};

export default MenuDetail;
