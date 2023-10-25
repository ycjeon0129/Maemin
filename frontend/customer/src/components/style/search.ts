import styled from 'styled-components';

export const SearchContainer = styled.div`
	display: flex;
	flex-direction: column;
`;

export const HistoryItem = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	background-color: white;
	border-bottom: 1px solid #f0f0f0;
	height: 50px;
	padding-left: 10px;
	:hover {
		cursor: pointer;
	}
`;

export const SearchBox = styled.div`
	display: flex;
	align-items: center;
	background-color: white;
	border-radius: 10px;
	box-shadow: 2px 2px 2px 0px rgba(0, 0, 0, 0.25);
	height: 40px;
	width: 345px;
	margin-top: 21px;
	margin-bottom: 11px;
`;

export const SearchingBox = styled.div`
	display: flex;
	align-items: center;
	background-color: white;
	height: 38px;
`;

export const SearchIconBox = styled.div`
	padding: 10px;
	cursor: pointer;
`;

export const SearchHistoryBox = styled.div`
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	background-color: white;
	height: 40px;
	width: 390px;
	border-bottom: 1px solid #bbb;
`;

export const HistoryBox = styled.div`
	display: flex;
	flex-direction: column;
`;

export const HistoryText = styled.span`
	color: #000;
	text-align: center;
	font-size: 18px;
	font-style: normal;
	font-weight: 400;
	line-height: 10px; /* 55.556% */
	padding-left: 10px;
`;

export const RemoveBox = styled.div`
	margin-right: 10px;
`;
