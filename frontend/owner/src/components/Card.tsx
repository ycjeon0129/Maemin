import React from 'react';
import { HomeCardContent, HomeCardTitle } from './text';
import { BtnBox, CardContainer, CardTextBox, ImgBox } from './style/card';
import Button from './Button';

const Card = ({ width, height, title, content, titleSize, imgSrc, flag }: CardProps) => {
	return (
		<CardContainer width={width} height={height}>
			<CardTextBox>
				<HomeCardTitle fontSize={titleSize}>{title}</HomeCardTitle>
				<HomeCardContent>{content}</HomeCardContent>
			</CardTextBox>

			{imgSrc ? (
				<>
					<ImgBox>
						<img src={imgSrc} alt="test" width={100} height={60} />
					</ImgBox>
					<BtnBox>
						<Button label={flag ? '품절 취소' : '품절'} margin="10px" />
					</BtnBox>
				</>
			) : null}
		</CardContainer>
	);
};

export default Card;
