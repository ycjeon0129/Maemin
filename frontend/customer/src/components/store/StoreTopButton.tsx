import React from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '../../store/store';
import BackarrowIcon from '../../assets/imgs/backarrow.svg';
import CartIcon from '../../assets/imgs/cart.svg';

const NavigationContainer = styled.div`
	width: 390px;
	height: 40px;
	position: fixed;
	top: 0;
	left: 50%;
	transform: translateX(-50%);
	display: flex;
	justify-content: space-between;
	padding: 25px 10px;
	background-color: rgba(255, 255, 255, 0.7);
	z-index: 10;
`;
const ItemCountSpan = styled.span`
	width: 20px;
	height: 20px;
	background-color: red;
	position: absolute;
	right: -5px;
	border-radius: 100%;
	top: -5px;
	display: flex;
	justify-content: center;
	align-items: center;
	color: rgba(255, 255, 255, 1);
`;

const BackButton = styled.button`
	background-color: transparent;
	border: none;
`;

const CartButton = styled.button`
	position: absolute;
	top: 0;
	right: 0;
	background-color: transparent;
	border: none;
	margin-top: 20px;
	margin-right: 10px;
`;

const StoreTopButton = () => {
	const navigate = useNavigate();
	const menuList = useSelector((state: RootState) => state.basket.menuList);
	const itemCount = menuList.length;

	const navigateToPreviousPage = () => {
		navigate(-1);
	};
	const moveToBasket = () => {
		navigate('/basket');
	};

	return (
		<NavigationContainer>
			<BackButton onClick={navigateToPreviousPage}>
				<img src={BackarrowIcon} alt="Go back" />
			</BackButton>
			<CartButton onClick={() => moveToBasket()}>
				<img src={CartIcon} alt="Cart" />
				{itemCount > 0 && <ItemCountSpan>{itemCount}</ItemCountSpan>}
			</CartButton>
		</NavigationContainer>
	);
};

export default StoreTopButton;
