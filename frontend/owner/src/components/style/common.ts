import styled from 'styled-components';

export const Container = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`;

export const FlexBox = styled.div<directionType>`
	display: flex;
	flex-direction: ${(props) => (props.dir === 'row' ? 'row' : 'column')};
	align-items: center;
	justify-content: center;
`;

export const HoverPointerBox = styled.div<HoverProps>`
	cursor: pointer;
	width: ${({ width }) => (width ? `${width}px` : '20px')};
`;

export const BoldText = styled.span<FontSizeProps>`
	color: #000;
	text-align: center;
	font-feature-settings:
		'clig' off,
		'liga' off;
	font-size: ${(props) => (props.fontSize ? props.fontSize : '20px')};
	font-weight: 600;
	line-height: 20px; /* 83.333% */
	letter-spacing: -0.5px;
	display: inline-block;
	margin: 20px 20px;
`;

export const HeaderBox = styled.div`
	margin: 12px;
`;
