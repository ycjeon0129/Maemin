import styled from 'styled-components';

export const HeaderContainer = styled.div`
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
	background: '#000';
	width: 100%;
	height: 100px;
`;

type HeaderProps = {
	url: string;
};

export const HeaderDiv = styled.div<HeaderProps>`
	position: relative;
	background-color: gray;
	background-color: ${(url) => (url ? 'gray' : 'blue')};
	width: 100%;
	height: 100px;
	display: flex;
	align-items: center;
	justify-content: center;
	text-align: center;
	:hover {
		cursor: pointer;
	}
	border: 1px solid '#f0f0f0';
`;
