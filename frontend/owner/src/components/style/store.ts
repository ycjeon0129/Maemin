import styled from 'styled-components';

const StoreInfoContainer = styled.div`
	position: relative;
	background-color: white;
	display: flex;
	flex-direction: column;
	margin-top: 10px;
	min-width: 925px;
	min-height: 305px;
`;

const NumberBox = styled.div`
	font-size: 20px;
	margin-left: 10px;
	margin-right: 10px;
`;
const TextBox = styled.div`
	font-size: 22px;
	margin-left: 10px;
	margin-right: 10px;
`;

const SmallTextBox = styled.div`
	font-size: 18px;
	margin-left: 5px;
	margin-right: 5px;
`;

const StyledImg = styled.img`
	height: 25px;
	width: 25px;
`;

const ContentBox = styled.div`
	display: flex;
	justify-content: flex-start;
	align-items: center;
	margin-left: 24px;
`;
const ContentItem = styled.div`
	display: flex;
	align-items: center;
`;

export { ContentItem, ContentBox, NumberBox, TextBox, SmallTextBox, StyledImg, StoreInfoContainer };
