export const dummyMenuData: MenuData[] = [
	{
		menuId: 2,
		storeId: 1,
		category: [1],
		name: '음식2',
		price: 13000,
		menuPictureUrl:
			'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fcdn.crowdpic.net%2Fdetail-thumb%2Fthumb_d_6E5CFD478A64807DB737AA9FB14BBCE4.png&type=a340',
		popularity: 0,
	},
	{
		menuId: 1,
		storeId: 2,
		category: [1],
		name: '음식1',
		price: 14000,
		menuPictureUrl:
			'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fcdn.crowdpic.net%2Fdetail-thumb%2Fthumb_d_6E5CFD478A64807DB737AA9FB14BBCE4.png&type=a340',
		popularity: 1,
	},
];
export const dummyOrderData: OrderData[] = [
	{
		orderId: 1,
		storeId: 1,
		userId: 1,
		paymentMethod: 1,
		totalPrice: 10000,
		requests: '요청사항...',
		createdDate: 'yyyy-mm-dd?',
		menus: [
			{
				menuId: 1,
				name: '메뉴1',
				price: 5000,
				quantity: 2,
				menuOptions: [
					{
						option: '곱빼기',
						price: 1000,
					},
					{
						option: '더 맵게',
						price: 0,
					},
				],
			},
			{
				menuId: 2,
				name: '메뉴2',
				price: 3000,
				quantity: 1,
				menuOptions: [
					{
						option: '곱빼기',
						price: 1000,
					},
					{
						option: '더 맵게',
						price: 0,
					},
				],
			},
		],
	},
];
