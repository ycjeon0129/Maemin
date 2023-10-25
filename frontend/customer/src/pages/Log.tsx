import React, { useEffect, useState } from 'react';
import { Container } from '../components/layout/common';
import Card from '../components/Card';
import { ReactComponent as LogBook } from '../assets/imgs/logbook.svg';
import { dummyOrderData } from '../assets/dummy';
// import { Stomp } from '@stomp/stompjs';

const orderData = dummyOrderData;
const menus = orderData[0].menus;

const Log = () => {
	// console.log(myLocation);

	// const socket = new SockJS('/cart');
	// const stompClient = Stomp.over(socket);
	// console.log(stompClient);
	// stompClient.connect({}, () => {
	// 	stompClient.subscribe(`/topic/${1}`, (message) => {
	// 		const cartUpdate = JSON.parse(message.body);
	// 		console.log(cartUpdate);
	// 	});
	// });
	// useEffect(() => {
	// 	stompClient.send(`/app/cart/${1}`, { name: 'asdf', message: 'hi' });
	// }, []);
	// topic일 때는 subscribe
	// app일 때는 publish
	// const client = new Client({
	// 	brokerURL: 'wss://j9c208.p.ssafy.io/cart-service/cart',
	// 	onConnect: () => {
	// 		try {
	// 			client.subscribe('/topic/cart/1', (message) => console.log(`Received:${message.body}`));
	// 			client.publish({ destination: '/app/cart/1', body: 'asdfasdf' });
	// 			console.log('연결 완료');
	// 		} catch (error) {
	// 			console.log(error);
	// 		}
	// 	},
	// });
	// useEffect(() => {
	// 	client.activate();
	// 	// const subscribe = () => {
	// 	// 	client.current.subscribe('/sub/topic/');
	// 	// };
	// }, []);
	const [orderState, setOrderState] = useState('');

	useEffect(() => {
		setOrderState('조리중...');
	}, []);

	{
		menus &&
			menus.map((item: OrderMenu) => {
				return <div key={item.menuId}>{item.name}</div>;
			});
	}

	return (
		<Container>
			<div>
				<Card
					title="현재 주문현황"
					content={orderState}
					icon={(props) => <LogBook {...props} />}
					iconSize={100}
				/>
				<Card title="과거 주문내역" menus={menus} icon={(props) => <LogBook {...props} />} iconSize={100} />
			</div>
		</Container>
	);
};

export default Log;
