import React from 'react';
import { useDispatch } from 'react-redux';
import { setMenu } from '../../store/menuSlice';
import styled from 'styled-components';
import MenuInfo from '../menu/MenuInfo';

interface MenuListProps {
	menu: MenuData[];
	title: string;
	iconSrc: string;
	popularity: number;
}

const MenuContainer = styled.div<{ popularity: number }>`
	position: relative;
	height: auto;
	background-color: white;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	margin-top: 20px;
	border: ${(props) => (props.popularity === 1 ? '3px solid rgba(255, 182, 73, 1)' : 'none')};
`;

const ContentContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: flex-start;
	justify-content: flex-start;
	margin: 10px 20px;
`;

const MenuName = styled.div`
	font-size: 24px;
	position: relative;
	margin-left: 10px;
`;

const MenuList = ({ menu, title, iconSrc, popularity }: MenuListProps) => {
	const dispatch = useDispatch();
	// 가격 세자리마다 쉼표 추가 ex) 1,000원

	const handleMenuClick = (menu: MenuData) => {
		dispatch(
			setMenu({
				menuId: menu.menuId,
				name: menu.name,
				price: menu.price,
				menuPictureUrl: menu.menuPictureUrl,
			}),
		);
	};

	return (
		<MenuContainer popularity={popularity}>
			<ContentContainer>
				<img src={iconSrc} alt="아이콘" />
				<MenuName>{title}</MenuName>
			</ContentContainer>
			{menu.map((item, index) => (
				<MenuInfo
					key={index}
					name={item.name}
					price={item.price}
					imageUrl={item.menuPictureUrl}
					menuId={item.menuId.toString()}
					onClick={() => handleMenuClick(item)}
				/>
			))}
		</MenuContainer>
	);
};

export default MenuList;
