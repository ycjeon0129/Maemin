import React, { useEffect, useState } from 'react';
import Card from '../components/Card';
import { Container, FlexBox } from '../components/style/common';
import testImg from '../assets/imgs/pastatest.png';
import { HomeTitle } from '../components/text';
import Button from '../components/Button';
import { RootState } from '../store/store';
import { useSelector } from 'react-redux';
import Modal from '../components/Modal';
import { useNavigate } from 'react-router-dom';

const StoreMenu = () => {
	const [isOpen, setIsOpen] = useState(false);
	const storeId = useSelector((state: RootState) => state.user.storeId);
	const menuList = useSelector((state: RootState) => state.store.menuList);
	const navigate = useNavigate();
	useEffect(() => {
		if (!(storeId! > 0)) {
			alert('등록된 매장이 없습니다');
			navigate('/');
		}
	});
	const addMenu = () => {
		// 메뉴추가
		console.log('메뉴추가');
		setIsOpen(!isOpen);
	};
	return (
		<Container>
			{isOpen && <Modal isOpen={isOpen} title={'ing'}></Modal>}
			<FlexBox dir="row">
				<HomeTitle>메뉴 리스트</HomeTitle>
				<Button
					label="메뉴 추가"
					onClick={addMenu}
					backgroundColor="rgba(255, 182, 73, 1)"
					fontSize="16px"
					margin="10px"
					textColor="white"
					borderRadius="100px"
					width={85}
					height={45}
					marginTop={20}
					borderColor="rgb(240, 240, 240)"
				/>
			</FlexBox>
			<FlexBox dir="column">
				<FlexBox dir="row">
					{menuList?.map((item) => {
						return (
							<Card
								title={item.name}
								content={`${item.price} 원`}
								titleSize="24px"
								imgSrc={item.menuPictureUrl}
								width={200}
							/>
						);
					})}
					<Card
						title="구라파스타"
						content="1234 원"
						titleSize="24px"
						imgSrc={testImg}
						width={200}
						flag={true}
					/>
				</FlexBox>
			</FlexBox>
		</Container>
	);
};

export default StoreMenu;
