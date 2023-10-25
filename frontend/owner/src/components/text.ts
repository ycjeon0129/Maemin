import styled from 'styled-components';

export const HomeTitle = styled.span`
	color: #000;
	text-align: center;
	font-feature-settings:
		'clig' off,
		'liga' off;
	font-size: '32px';
	font-weight: 600;
	line-height: 20px; /* 83.333% */
	letter-spacing: -0.5px;
	display: inline-block;
	margin-top: 15px;
	margin-left: 10px;
`;
export const HomeCardTitle = styled.span<FontSizeProps>`
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
	margin-top: 15px;
	margin-left: 10px;
`;

export const HomeCardContent = styled.span`
	color: rgba(0, 0, 0, 0.5);
	text-align: center;
	font-feature-settings:
		'clig' off,
		'liga' off;
	font-size: 15px;
	font-weight: 400;
	line-height: 20px; /* 133.333% */
	letter-spacing: -0.5px;
	margin-left: 15px;
`;

export const CarouselTitle = styled.span`
	color: #000;
	font-feature-settings:
		'clig' off,
		'liga' off;
	font-size: 18px;
	font-weight: 600;
	line-height: 40px; /* 55.556% */
	padding-left: 15px;
`;

export const StoreName = styled.span`
	color: #000;
	font-feature-settings:
		'clig' off,
		'liga' off;
	font-size: 12px;
	font-weight: 600;
	line-height: 40px; /* 55.556% */
`;
