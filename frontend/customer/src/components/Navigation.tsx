import React from 'react';
import styled from 'styled-components';
import BackarrowIcon from '../assets/imgs/backarrow.svg';
import Cart from '../assets/imgs/cart.svg';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';
const NavContainer = styled.div`
	padding: 2%;
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: white;
	border-bottom: 1px solid rgba(0, 0, 0, 0.1);
`;
const NavTitleBox = styled.div`
	font-weight: bold;
`;
const NavIconBox = styled.div`
	position: relative;
`;
const NavIconItem = styled.div`
	background-color: red;
	width: 20px;
	height: 20px;
	position: absolute;
	right: -7px;
	top: -7px;
	color: white;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 10px;
`;
const Navigation = ({ title }: NavigationProps) => {
	const cartMenuList = useSelector((state: RootState) => state.basket);
	const navigate = useNavigate();
	return (
		<NavContainer>
			<NavIconBox
				onClick={() => {
					navigate(-1);
				}}
			>
				<img src={BackarrowIcon} />
			</NavIconBox>
			<NavTitleBox>{title}</NavTitleBox>
			<NavIconBox
				onClick={() => {
					navigate('/basket');
				}}
			>
				<img src={Cart} />
				<NavIconItem>{cartMenuList.menuList.length}</NavIconItem>
			</NavIconBox>
		</NavContainer>
	);
};
export default Navigation;
