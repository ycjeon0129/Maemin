import React from 'react';
import { BasketPriceContainer, BasketPriceBox } from '../style/basket';
interface PriceProps {
	title: string;
	price: number;
}

function BasketPriceInfo({ title, price }: PriceProps) {
	// 가격 세자리마다 쉼표 추가 ex) 1,000원
	const addRest = (price: number) => {
		return price.toLocaleString('ko-KR') + '원';
	};
	return (
		<BasketPriceContainer>
			<BasketPriceBox>{title}</BasketPriceBox>
			<BasketPriceBox>{addRest(price)}</BasketPriceBox>
		</BasketPriceContainer>
	);
}
export default BasketPriceInfo;
