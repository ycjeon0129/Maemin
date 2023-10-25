// FixedHeaderComponent.js 혹은 FixedHeaderComponent.tsx 파일을 생성

import React from 'react';
import styled from 'styled-components';
import BackarrowIcon from '../../assets/imgs/backarrow.svg';
import CartIcon from '../../assets/imgs/cart.svg';

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
import { useNavigate } from 'react-router-dom';
const FixedHeader = styled.div`
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	width: 390px;
	margin: auto;
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: transparent;
	z-index: 1000;
`;

const BackButton = styled.button`
	position: absolute;
	top: 0;
	left: 0;
	background-color: transparent;
	border: none;
	margin-top: 25px;
	margin-left: 10px;
	z-index: 3;
`;

const CartButton = styled.button`
	position: absolute;
	top: 0;
	right: 0;
	background-color: transparent;
	border: none;
	margin-top: 20px;
	margin-right: 10px;
	z-index: 3;
`;

interface FixedHeaderProps {
	selectedMenuName: string;
	onBackClick: () => void;
	itemCount: number;
}

const FixedHeaderComponent = ({ onBackClick, itemCount }: FixedHeaderProps) => {
	const navigate = useNavigate();
	const moveToBasket = () => {
		navigate('/basket');
	};
	return (
		<FixedHeader>
			<BackButton onClick={onBackClick}>
				<img src={BackarrowIcon} alt="Go back" />
			</BackButton>
			<CartButton onClick={() => moveToBasket()}>
				<img src={CartIcon} alt="Share" />
				{itemCount > 0 && <ItemCountSpan>{itemCount}</ItemCountSpan>}
			</CartButton>
		</FixedHeader>
	);
};

export default FixedHeaderComponent;
