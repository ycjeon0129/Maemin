import React from 'react';
import styled from 'styled-components';
import { HomeCardContent, HomeCardTitle } from './text';
import { useNavigate } from 'react-router-dom';

export const CardContainer = styled.div<CardProps>`
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	background-color: white;
	box-shadow: 1 1;
	width: ${({ width }) => (width ? `${width}px` : '345px')};
	height: ${({ height }) => (height ? `${height}px` : '100%')};
	border-radius: 10px;
	background: #fff;
	box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
	margin: 21px 14px;
	:hover {
		cursor: pointer;
	}
	position: relative;
`;

const CardTextBox = styled.div`
	display: flex;
	flex-direction: column;
	align-items: flex-start;
`;

const ImgBox = styled.div`
	width: 100%;
	display: flex;
	justify-content: flex-end;
	padding-right: 15px;
	padding-bottom: 10px;
`;
const AlertBox = styled.div`
	width: 20px;
	height: 20px;
	background-color: red;
	position: absolute;
	right: -5px;
	border-radius: 100%;
	top: -5px;
	display: flex;
	justify-content: center;
	color: rgba(255, 255, 255, 1);
`;
const Card = ({
	icon,
	iconSize,
	width,
	height,
	title,
	content,
	titleSize,
	url,
	count,
	menus,
}: IconProps & CardProps) => {
	const navigate = useNavigate();
	const alertBasket = url == 'basket';
	return (
		<CardContainer
			width={width}
			height={height}
			onClick={() => {
				navigate(`${url}`);
			}}
		>
			<CardTextBox>
				<HomeCardTitle fontSize={titleSize}>{title}</HomeCardTitle>
				<HomeCardContent>{content}</HomeCardContent>
				{menus?.map((item: OrderMenu) => {
					return (
						<div key={item.menuId}>
							<HomeCardContent>이름 :{item.name}</HomeCardContent>
							<HomeCardContent>가격 : {item.price}</HomeCardContent>
						</div>
					);
				})}
			</CardTextBox>
			<ImgBox>{React.createElement(icon, { width: iconSize, height: iconSize })}</ImgBox>
			{alertBasket ? <AlertBox>{count}</AlertBox> : ''}
		</CardContainer>
	);
};

export default Card;
