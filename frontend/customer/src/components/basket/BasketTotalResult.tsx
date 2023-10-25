import BasketTotalPrice from './BasketTotalPrice';
import { useSelector } from 'react-redux';
import { RootState } from '../../store/store';
import { BasketTotalContainer, BasketTotalTitleBox } from '../style/basket';

function BasketTotalResult() {
	// const basketTotal = useSelector((state: RootState) => state.basket.totalPrice);
	const pickedMenuPrice = useSelector((state: RootState) => state.basket.pickedMenuPrice);

	return (
		<BasketTotalContainer>
			<BasketTotalTitleBox>결제 금액</BasketTotalTitleBox>
			<BasketTotalPrice title={'총 결제 금액'} price={pickedMenuPrice}></BasketTotalPrice>
			<BasketTotalPrice title={'내 결제 예정 금액'} price={pickedMenuPrice}></BasketTotalPrice>
		</BasketTotalContainer>
	);
}
export default BasketTotalResult;
