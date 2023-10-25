import React, { PropsWithChildren } from 'react';
import styled from 'styled-components';
import downT from '../assets/imgs/down triangle.png';

const SelectDiv = styled.div<SelectProps>`
	width: ${(props) => (props.width ? `${props.width}` : '100%')};
	display: flex;
	flex-direction: column;
`;

const SelectContentBox = styled.div`
	width: 100%;
	height: 50%;
	display: flex;
	padding: 0px 10px;
	background-color: white;
	border-radius: 5px;
	align-items: center;
	border: 1px solid rgba(0, 0, 0, 0.2);
`;
const SelectItemSelect = styled.select`
	width: 100%;
	padding: 10px 15px;
	border-radius: 5px;
	position: relative;
	border: none;
	z-index: 3;
	&:focus {
		outline: none;
	}
`;
const SelectItemIcon = styled.span`
	opacity: 0.5;
	z-index: 4;
	& > img {
		width: 15px;
	}
`;
const SelectOption = styled.option`
	width: 100%;
	height: 10%;
	border-radius: 5px;
`;

const Select = ({ list, width }: PropsWithChildren<SelectProps>) => {
	return (
		<SelectDiv width={width}>
			<SelectContentBox>
				<SelectItemSelect>
					{list ? (
						list.map((item, index) => <SelectOption key={index}>{item}</SelectOption>)
					) : (
						<SelectOption>선택 없음.</SelectOption>
					)}
				</SelectItemSelect>
				<SelectItemIcon>
					<img src={downT} />
				</SelectItemIcon>
			</SelectContentBox>
		</SelectDiv>
	);
};

export default Select;
