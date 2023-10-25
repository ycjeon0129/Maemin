import styled from 'styled-components';

export const Container = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
`;

// height: ${(height) => (height ? height : '1000px')};
export const TallContainer = styled.div<TallProps>`
	height: ${(props) => (props.height ? props.height : '1000px')};
`;

export const HoverPointerBox = styled.div<HoverProps>`
	cursor: pointer;
	width: ${({ width }) => (width ? `${width}px` : '20px')};
`;
