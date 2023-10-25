import React from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const MenuInfoContainer = styled.div`
	width: 85%;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	margin: 0 auto;
	margin-bottom: 5px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.2);
	&:last-child {
		border-bottom: 0;
	}
`;

const MenuDetail = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	justify-contents: space-between;
	& > div {
		margin: 10px 0;
	}
`;

const MenuName = styled.div`
	font-size: 15px;
	margin: 10px;
	margin-bottom: 10px;
	white-space: nowrap;
	margin-left: 10px;
`;

const MenuPrice = styled.div`
	font-size: 15px;
	color: rgba(0, 0, 0, 0.5);
	margin-left: 10px;
`;
const MenuImageBox = styled.div``;

const MenuImage = styled.img`
	width: 80px;
	height: 68px;
	object-fit: cover;
	border-radius: 25px;
	align-self: center;
	margin-bottom: 10px;
`;

interface MenuInfoProps {
	name: string;
	price: number;
	imageUrl: string;
	menuId: string;
	onClick: () => void;
}

const MenuInfo = (props: MenuInfoProps) => {
	const { name, price, imageUrl, menuId, onClick } = props;
	const navigate = useNavigate();
	// 가격 세자리마다 쉼표 추가 ex) 1,000원
	const addRest = (price: number) => {
		return price.toLocaleString('ko-KR') + '원';
	};
	const handleCombinedClick = () => {
		onClick();
		navigate(`/menu-detail/${menuId}`);
	};

	return (
		<MenuInfoContainer onClick={handleCombinedClick}>
			<MenuDetail>
				<MenuName>{name}</MenuName>
				<MenuPrice>{addRest(price)}</MenuPrice>
			</MenuDetail>
			<MenuImageBox>
				<MenuImage src={imageUrl} alt={name} />
			</MenuImageBox>
		</MenuInfoContainer>
	);
};

export default MenuInfo;
