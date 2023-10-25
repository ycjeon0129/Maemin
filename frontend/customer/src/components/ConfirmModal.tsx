// ConfirmModal.tsx
import React from 'react';
import styled from 'styled-components';
import Button from './Button';
import signCheck from '../assets/imgs/sIgnCheck.svg';

const ModalOverlay = styled.div`
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	display: flex;
	justify-content: center;
	align-items: center;
`;

const ModalContainer = styled.div`
	background-color: #fff;
	height: 200px;
	width: 250px;
	padding: 20px;
	border-radius: 8px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
`;

const Message = styled.div`
	flex-grow: 3;
	display: flex;
	align-items: center;
	justify-content: center;
`;

interface ConfirmModalProps {
	message: string;
	onConfirm: () => void;
	onCancel: () => void;
}

const ConfirmModal = ({ message, onConfirm, onCancel }: ConfirmModalProps) => {
	return (
		<ModalOverlay onClick={onCancel}>
			<ModalContainer onClick={(e) => e.stopPropagation()}>
				<img src={signCheck} alt="Check Sign" />
				<Message>{message}</Message>

				<Button
					label="확인"
					backgroundColor="#ffb649"
					textColor="white"
					width={100}
					height={40}
					onClick={onConfirm}
				/>
			</ModalContainer>
		</ModalOverlay>
	);
};

export default ConfirmModal;
