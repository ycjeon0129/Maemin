import React from 'react';
import styled from 'styled-components';
import Button from '../Button';

interface FoodCountProps {
	quantity: number;
	setQuantity: React.Dispatch<React.SetStateAction<number>>;
}

const FoodCountContainer = styled.div`
	position: relative;
	height: 50px;
	background-color: white;
	display: flex;
	flex-direction: row;
	align-items: center;
	margin-top: 10px;
`;

const CountName = styled.div`
	font-size: 24px;
	position: relative;
	margin-left: 10px;
	flex-grow: 1;
`;

const CountBox = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	margin-right: 20px;
`;

const FoodCount = ({ quantity, setQuantity }: FoodCountProps) => {
	const handleIncrement = () => {
		setQuantity((prevQuantity) => {
			const newQuantity = prevQuantity + 1;
			console.log('Incrementing quantity:', newQuantity);
			return newQuantity;
		});
	};

	const handleDecrement = () => {
		if (quantity > 1) {
			setQuantity((prevQuantity) => {
				const newQuantity = prevQuantity - 1;
				console.log('Decrementing quantity:', newQuantity);
				return newQuantity;
			});
		}
	};

	return (
		<FoodCountContainer>
			<CountName>수량</CountName>
			<CountBox>
				<Button label="-" onClick={handleDecrement} width={20} height={20} margin="5px" />
				{quantity}
				<Button label="+" onClick={handleIncrement} width={20} height={20} margin="5px" />
			</CountBox>
		</FoodCountContainer>
	);
};

export default FoodCount;
