import styled, { keyframes } from 'styled-components';
// Payment
export const PaymentContainer = styled.div`
	& > div:nth-child(n + 1) > div:first-child {
		margin: 0 10px;
	}
`;
export const PaymentTitleItem = styled.div`
	padding: 10px 0;
	font-weight: bold;
	border-bottom: 1px solid rgba(0, 0, 0, 0.1);
`;
export const PaymentMenuBox = styled.div`
	background-color: white;
	margin: 0 auto;
`;
export const PaymentMenuInfoBox = styled.div`
	margin: 0 auto;
	margin-bottom: 10px;
	background-color: white;
	border-radius: 0 0 5px 5px;
	padding: 0 10px;
`;
export const PaymentMenuInfoItem = styled.div`
	padding: 10px;
	display: flex;
	justify-content: space-between;
`;
export const PaymentMenuInfo = styled.div``;

export const PaymentRequestBox = styled.div`
	margin: 10px auto;
	background-color: white;
`;
export const PaymentRequestContentItem = styled.div`
	padding: 10px;
`;

export const PaymentMethodBox = styled.div`
	background-color: white;
	margin: 10px auto;
`;

export const PaymentMethodContentBox = styled.div<{ selected: boolean }>`
	display: flex;
	align-items: center;
	padding: 15px 10px;
	background-color: ${(props) => (props.selected ? 'rgba(100, 100, 0, 0.2)' : 'white')};
	justify-content: center;
	transition: background-color 0.2s ease-in-out;
	& > :first-child {
		margin-right: 10%;
	}
`;

export const PaymentMethodContentItem = styled.div`
	width: 40%;
`;
export const PaymentMethodContentImg = styled.img`
	width: 50px;
	height: 20px;
	margin-right: 10px;
`;

// PayRegist
export const PayRegistContainer = styled.div`
	background-color: white;
	& > :nth-child(n + 2) > * {
		margin: 15px 0;
	}
	& > :last-child {
		margin-top: 150px;
	}
`;
export const PayRegistContentBox = styled.div`
	width: 80%;
	margin: 1px auto;
`;
export const PayRegistTitleItem = styled.div`
	font-weight: bold;
	font-size: 14px;
`;
export const PayRegistInputItem = styled.div`
	display: flex;
	justify-content: flex-start;
	align-items: center;
	margin-left: 6%;
	margin: auto;
	& input[type='number']::-webkit-outer-spin-button,
	input[type='number']::-webkit-inner-spin-button {
		-webkit-appearance: none;
		margin: 0;
	}
	& input:nth-child(n) {
		margin: 0 5px;
	}
	& input:first-child {
		margin-left: 0;
	}
	& input:last-child {
		margin-right: 0;
	}
`;
export const PayRegistButtonItem = styled.button`
	width: 100%;
	margin: 30% 0 10% 0;
	padding: 10px;
	border-radius: 5px;
	border: none;
	background-color: rgba(255, 182, 73, 0.9);
	color: white;
	font-weight: bold;
`;

// PayPassword
export const PayPasswordContainer = styled.div`
	display: flex;
	height: 90vh;
	flex-direction: column;
	justify-content: space-between;
	background-color: white;
`;
const newAnimation = () => keyframes`
	50%{
		transform: translateX(10px);
	}
	100%{
		transform: translateX(-10px);
	}
`;
export const PayPasswordInputBox = styled.div<{ checker: boolean }>`
	display: flex;
	justify-content: center;
	font-size: 32px;
	margin: 16px 0;
	animation: ${(props) => (props.checker ? '' : newAnimation)} 0.1s 2;
`;
export const PayPasswordInputItem = styled.div`
	opacity: 0.2;
	&.active {
		opacity: 1;
	}
`;

export const PayPasswordMessageBox = styled.div<{ checker: boolean }>`
	display: flex;
	justify-content: center;
	color: rgba(255, 0, 0, 0.6);
	visibility: ${(props) => (props.checker ? 'hidden' : 'visible')};
`;

export const PayPasswordButtonBox = styled.div`
	bottom: 0;
	display: grid;
	grid-template-rows: repeat(4, 25%);
	grid-template-columns: repeat(3, 33%);
	align-items: center;
	justify-items: center;
`;
export const PayPasswordButtonItem = styled.div`
	width: 100%;
	display: flex;
	justify-content: center;
	padding: 20% 0;
	cursor: pointer;
`;

//MyPay
export const MyPayContainer = styled.div``;
export const MyPayBox = styled.div`
	height: 85vh;
	background-color: black;
	& * {
		padding: 5px 0;
	}
	position: relative;
`;
export const MyPayDateBox = styled.div`
	display: flex;
	width: 93%;
	justify-content: flex-end;
	color: white;
	padding-top: 150px;
`;
export const MyPayImgBox = styled.div`
	max-width: 390px;
	width: auto;
	display: flex;
	overflow: scroll;
	padding: 5% 0;
	white-space: no-wrap;
`;
export const MyPayImgItem = styled.div`
	margin: 0 12%;
	width: 100%;
`;
export const MyPayImg = styled.img`
	background-color: black;
	padding: 0 60px;
	width: 50px;
	max-height: 170px;
	max-width: 390px;
`;

export const MyPayInfoBox = styled.div`
	width: 55%;
	margin: 0 auto;
	color: white;
	font-size: 12px;
	font-weight: bold;
`;
export const MyPayInfoItem = styled.div`
	display: flex;
	justify-content: space-between;
`;
export const MyPayInfoTitle = styled.div``;
export const MyPayInfoValue = styled.div``;
export const MyPayButtonBox = styled.div`
	width: 60%;
	margin: 30px auto;
`;
