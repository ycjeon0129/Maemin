import React, { PropsWithChildren } from 'react';
import styled from 'styled-components';

const IconDiv = styled.div`
	:hover {
		cursor: pointer;
		background-color: #999;
	}
	display: flex;
	align-items: center;
	justify-content: center;
	min-width: 70px;
	min-height: 70px;
`;

const IconBox = ({ icon, iconSize }: PropsWithChildren<IconProps>) => {
	return <IconDiv>{React.createElement(icon, { width: iconSize, height: iconSize })}</IconDiv>;
};

export default IconBox;
