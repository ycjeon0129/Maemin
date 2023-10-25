import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import checkIcon from '../assets/imgs/checkIcon.svg';
import failIcon from '../assets/imgs/failIcon.svg';
const ModalContainer = styled.div`
	position: fixed;
	display: flex;
	align-items: center;
	flex-direction: column;
	justify-content: center;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 1000;
	width: 390px;
	height: 100%;
`;
const ModalContentBox = styled.div`
	background-color: white;
	width: 300px;
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
	const modalList = [
		{
			code: 'noPay',
			title: '페이 미등록',
			content: '간편 페이 미등록 상태입니다.\n 등록 페이지로 이동합니다.',
		},
		{
			code: 'payRegist',
			title: '페이 등록 완료',
			content: '간편 페이 가입이 완료되었습니다.',
		},
		{
			code: 'paymentComplete',
			title: '결제 완료',
			content: '결제가 완료되었습니다.',
		},
		{
			code: 'paymentFail',
			title: '결제 실패',
			content: '결제 실패하였습니다.',
		},
		{
			code: 'loginRequest',
			title: ' 결제 실패',
			content: '로그인이 필요한 기능입니다.',
		},
	];
	const codeNum = modalList.findIndex((item) => item.code == title);
	useEffect(() => {
		setModalState(true);
		setTimeout(() => {
			setModalState(false);
			return;
		}, 3000);
	}, []);
	return (
		<>
			{modalState && (
				<ModalContainer>
					<ModalContentBox>
						<ModalTitleItem>{modalList[codeNum].title}</ModalTitleItem>
						<ModalImgItem>
							<ModalImg src={modalList[codeNum].code == 'paymentFail' ? failIcon : checkIcon}></ModalImg>
						</ModalImgItem>
						<ModalContentItem>{modalList[codeNum].content}</ModalContentItem>
					</ModalContentBox>
				</ModalContainer>
			)}
		</>
	);
};
export default Modal;
