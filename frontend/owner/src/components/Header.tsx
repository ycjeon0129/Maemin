import React, { useEffect, useState } from 'react';
import { HeaderContainer, HeaderDiv } from './style/header';
import { BoldText } from './style/common';
import { useNavigate } from 'react-router-dom';
import { AlertBox } from './style/order';
import { RootState } from '../store/store';
import { useSelector, useDispatch } from 'react-redux';
import { setFlag } from '../store/sseSlice';

const Header = () => {
	const [selected, setSelected] = useState('');
	const HeaderList = [
		{ name: '주문 접수', url: '/order' },
		{ name: '매장 관리', url: '/store-management' },
		{ name: '메뉴 관리', url: '/store-menu' },
		{ name: '매장 정보', url: '/store-info' },
	];
	const navigate = useNavigate();
	const dispatch = useDispatch();
	const storeId = useSelector((state: RootState) => state.user.storeId);
	const flag = useSelector((state: RootState) => state.sse.flag);
	console.log(storeId);
	const receiveMsg = 'new';

	useEffect(() => {
		if (storeId! > 0) {
			const eventSource = new EventSource(`https://j9c208.p.ssafy.io/cart-service/connect/${storeId}`);

			eventSource.onopen = () => {
				// 연결 시 할 일
				console.log('연결됨');
			};

			// 이거 동작 안함요
			// eventSource.onmessage = async (event) => {
			// 	console.log(event);
			// 	// setTest(JSON.parse(event.data));
			// 	// alert(test);
			// 	setFlag(true);
			// 	console.log(flag);
			// };

			eventSource.addEventListener('order', (event) => {
				console.log(event);
				dispatch(setFlag({ flag: true }));
				console.log(flag);
			});

			// eslint-disable-next-line @typescript-eslint/no-explicit-any
			eventSource.onerror = (e: any) => {
				console.log(e);
				// 종료 또는 에러 발생 시 할 일
				eventSource.close();

				if (e.error) {
					// 에러 발생 시 할 일
				}

				if (e.target.readyState === EventSource.CLOSED) {
					// 종료 시 할 일
					console.log('연결종료');
					flag && dispatch(setFlag({ flag: true }));
				}
			};
		}
	}, [storeId]);

	const readMsg = () => {
		flag && dispatch(setFlag({ flag: false }));
	};

	return (
		<HeaderContainer>
			{HeaderList.map((item, i) => {
				return (
					<HeaderDiv
						key={i}
						onClick={() => {
							setSelected(item.name);
							navigate(item.url);
							i === 0 && readMsg();
						}}
						// style={item.url === location.href && 'background: red'}
						url={selected}
					>
						<BoldText>{item.name}</BoldText>
						{i === 0 && flag && <AlertBox>{receiveMsg}</AlertBox>}
					</HeaderDiv>
				);
			})}
		</HeaderContainer>
	);
};

export default Header;
