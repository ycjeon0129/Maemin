import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import checkIcon from '../assets/imgs/checkIcon.svg';
import failIcon from '../assets/imgs/failIcon.svg';
import Input from './Input';
import Button from './Button';
import { registMenu } from '../api/store';
import { RootState } from '../store/store';
import { useSelector } from 'react-redux';

const ModalContainer = styled.div`
	position: fixed;
	display: flex;
	align-items: center;
	flex-direction: column;
	justify-content: center;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 1000;
	width: 100vw;
	height: 100%;
`;
const ModalContentBox = styled.div`
	background-color: white;
	width: 500px;
	border-radius: 10px;
	& > div {
		display: flex;
		justify-content: center;
		align-items: center;
	}
`;
const ModalImg = styled.img``;
const ModalImgItem = styled.div`
	margin: 30px 0;
`;

const ModalTitleItem = styled.div`
	padding: 20px;
	margin: 0 10px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.3);
	font-weight: bold;
`;
const ModalContentItem = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0 10px 50px 10px;
	white-space: pre-wrap;
`;

interface booleanProps {
	isOpen: boolean;
	title: string;
}
const Modal = ({ isOpen, title }: booleanProps) => {
	const [modalState, setModalState] = useState<boolean>(isOpen);
	const [name, setName] = useState<string>('');
	const [price, setPrice] = useState<string>('');
	const [category, setCategory] = useState<string>('');
	const storeId = useSelector((state: RootState) => state.user.storeId);
	const modalList = [
		{
			code: 'complete',
			title: '메뉴 등록 완료',
			content: '메뉴 추가가 완료되었습니다.',
		},
		{
			code: 'ing',
			title: '메뉴 등록 창',
			content: '메뉴를 추가중입니다.',
		},
		{
			code: 'fail',
			title: '메뉴 등록 실패',
			content: '메뉴 추가에 실패하였습니다.',
		},
	];
	const codeNum = modalList.findIndex((item) => item.code == title);
	useEffect(() => {
		setModalState(true);
		setTimeout(() => {
			setModalState(false);
			return;
		}, 100000);
	}, []);

	const handleRegist = () => {
		registMenu({ name: name, price: Number(price), category: category, storeId: storeId! });
	};
	return (
		<>
			{modalState && (
				<ModalContainer>
					<ModalContentBox>
						<ModalTitleItem>{modalList[codeNum].title}</ModalTitleItem>
						<Input
							value={name}
							placeholder="상품명"
							type="text"
							onChange={setName}
							width={300}
							height={64}
							borderRadius="20px"
							border="white"
							margin="10px"
							paddingLeft="30px"
						/>
						<Input
							value={price}
							placeholder="금액"
							type="number"
							onChange={setPrice}
							width={300}
							height={64}
							borderRadius="20px"
							border="white"
							margin="10px"
							paddingLeft="30px"
						/>
						<Input
							value={category}
							placeholder="상품 분류"
							type="text"
							onChange={setCategory}
							width={300}
							height={64}
							borderRadius="20px"
							border="white"
							margin="10px"
							paddingLeft="30px"
						/>
						<Button
							label="상품 등록하기"
							onClick={handleRegist}
							backgroundColor="rgba(255, 182, 73, 1)"
							fontSize="16px"
							margin="12px"
							textColor="white"
							borderRadius="100px"
							width={305}
							height={64}
							borderColor="rgb(240, 240, 240)"
						/>
						<ModalImgItem>
							<ModalImg src={modalList[codeNum].code == 'fail' ? failIcon : checkIcon}></ModalImg>
						</ModalImgItem>
						<ModalContentItem>{modalList[codeNum].content}</ModalContentItem>
					</ModalContentBox>
				</ModalContainer>
			)}
		</>
	);
};
export default Modal;
