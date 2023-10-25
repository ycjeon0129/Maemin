import styled from 'styled-components';
// basket
export const BasketContainer = styled.div`
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	overflow-y: scroll;
	::-webkit-scrollbar {
		display: none;
	}
`;
export const BasketBox = styled.div`
	position: relative;
	width: 100%;
`;
export const BasketStoreInfoBox = styled.div<{ basketCheck: boolean }>`
	padding: ${(props) => (props.basketCheck ? '70px 30px' : '10px')};
	font-weight: ${(props) => (props.basketCheck ? 'bold' : '')};
	display: ${(props) => (props.basketCheck ? 'flex' : '')};
	justify-content: ${(props) => (props.basketCheck ? 'center' : '')};
	margin: 0 auto;
	border-bottom: 1px solid rgba(0, 0, 0, 0.1);
	background-color: white;
`;
export const BasketMenuListInfoBox = styled.div`
	margin: 0 auto;
	margin-bottom: 10px;
	background-color: white;
	& > div {
		margin: 0 10px;
	}
	& > :not(:last-child) {
		border-bottom: 1px solid rgba(0, 0, 0, 0.1);
	}
`;
// basket 메뉴 추가 컴포넌트
export const BasketAddContainer = styled.div`
	width: 90%;
	margin: 0 auto;
	margin-top: 15px;
`;
// basket 메뉴 상세 가격
export const BasketDetailContainer = styled.div`
	padding: 5px 0;
	width: 100%;
`;
export const BasketDetailInfoBox = styled.div`
	display: flex;
	justify-content: space-between;
	padding: 3px 10px;
	width: 100%;
`;
export const BasketDetailButtonItem = styled.div`
	transform: rotate(0);
	transition: all 0.5s;
	&.rotate {
		transform: rotate(-180deg);
	}
`;
export const BasketDetailPriceItem = styled.div`
	font-weight: bold;
`;
export const BasketDetailPayerBox = styled.div<{ toggleInfo: boolean }>`
	border-radius: 5px;
	background-color: rgba(0, 0, 0, 0.1);
	height: 0;
	transform: scaleY(0);
	visibility: ${(props) => (props.toggleInfo ? 'visible' : 'hidden')};
	transition: all 0.2s;
	&.act {
		height: 90%;
		transform: scaleY(1);
	}
`;
export const BasketDetailPayerItem = styled.div`
	width: 90%;
	margin: 0 auto;
	border-bottom: 1px solid rgba(0, 0, 0, 0.3);
	&:first-child {
		font-weight: bold;
		padding: 10px 0;
		font-size: 14px;
	}
	&:nth-child(n + 2) {
		padding: 10px 30px;
		font-size: 12px;
	}
`;
export const BasketDetailPayerNameItem = styled.div``;
export const BasketDetailPayerListItem = styled.div`
	width: 90%;
	padding: 10px 5px;
	margin: 0 auto;
	display: flex;
`;
export const BasketDetailPayerListTag = styled.div`
	width: 20%;
`;
export const BasketDetailPayerList = styled.div`
	width: 80%;
	display: flex;
	justify-content: flex-start;
	word-break: keep-all;
`;
export const BasketDetailResultItem = styled.div`
	width: 90%;
	margin: 0 auto;
	display: flex;
	font-size: 12px;
	padding: 10px;
	justify-content: space-between;
`;
export const BasketDetailPayerResult = styled.div`
	&:first-child {
		font-weight: bold;
	}
`;

// BasketMenuInfo
export const BasketMenuContainer = styled.div``;
export const BasketMenuTitleBox = styled.div`
	font-size: 16px;
	font-weight: bold;
	padding: 10px 0px;
	display: flex;
	justify-content: center;
	align-items: center;
`;
export const BasketMenuTitleItem = styled.div`
	width: 80%;
`;
export const BasketMenuTitleBtn = styled.div`
	width: 20%;
	display: flex;
	justify-content: flex-end;
`;
export const BasketMenuInfoBox = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;

export const BasketMenuImg = styled.img`
	width: 60px;
	height: 60px;
	border-radius: 5px;
	object-fit: contains;
`;
export const BasketMenuInfoItem = styled.div`
	width: 100%;
	padding: 10px;
	display: flex;
	justify-content: space-between;
`;
export const BasketMenuOption = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	width: 100%;
	& > div:first-child {
		margin-bottom: 20px;
	}
	margin-left: 10px;
`;
export const BasketMenuName = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	width: 80%;
	font-size: 14px;
`;
export const BasketMenuCount = styled.div`
	display: flex;
	justify-content: flex-end;
	font-size: 12px;
	align-items: center;
`;
export const BasketMenuCountBtn = styled.button`
	border: none;
	display: flex;
	justify-content: flex-end;
	font-size: 8px;
	font-color: blue;
	background-color: blue;
	align-items: center;
	background-color: white;
	border: 1px solid rgba(0, 0, 0, 0.1);
	padding: 6px;
`;
export const BasketMenuPriceItem = styled.div`
	display: flex;
	padding-top: 10px;
	justify-content: flex-end;
`;

// BasketPayBtn
export const BasketPayBtnContainer = styled.div`
	width: 90%;
	margin: 0 auto 20px auto;
`;

//BasketTotalPrice
export const BasketPriceContainer = styled.div`
	width: 80%;
	margin: 0 auto;
	display: flex;
	justify-content: space-between;
	padding-bottom: 10px;
	font-size: 14px;
`;
export const BasketPriceBox = styled.div`
    width::50%;
	margin-top:5px;
`;

// BasketTotalResult
export const BasketTotalContainer = styled.div`
	background-color: white;
	& > :first-child {
		margin: 0 10px;
	}
`;
export const BasketTotalTitleBox = styled.div`
	margin: 0 auto;
	padding: 10px 0;
	font-weight: bold;
	border-bottom: 1px solid rgba(0, 0, 0, 0.1);
`;
