/// <reference types="react-scripts" />

interface CardProps {
	width?: number;
	height?: number;
	title?: string;
	content?: string;
	titleSize?: string;
	imgSrc?: string;
	flag?: boolean;
}

interface IconProps {
	iconSize?: number;
	icon: IconType;
}

type FontSizeProps = {
	fontSize?: string;
};

type HoverProps = {
	width?: number;
	height?: number;
};

type directionType = {
	dir: string;
};

interface Todo {
	id: string;
	todo: string;
	isDone: boolean;
}

interface DragItemList {
	dragItems: DragItem[];
}

interface SignupForm {
	loginId: string;
	loginPw: string;
	userName: string;
	nickName: string;
	phone: string;
	sex: boolean; //-> False=남자 / True=여자
	age: number;
	role: string; // ROLE_CUSTOMER or ROLE_OWNER
}

interface User {
	username: string;
	nickname: string;
	role: string;
}

interface LoginForm {
	loginId: string;
	loginPw: string;
}

interface LoginRes {
	code: string;
	// eslint-disable-next-line @typescript-eslint/no-explicit-any
	userInfo: any;
	data: {
		accessToken: string;
		expiredTime: string;
	};
}

interface MenuOption {
	option: string;
	price: number;
}

interface OrderMenu {
	menuId: number;
	name: string;
	price: number;
	quantity: number;
	menuOptions?: MenuOption[];
}

interface OrderData {
	status: number;
	orderId: number;
	storeId: number;
	userId: number;
	totalPrice: number;
	requests: string;
	createdDate: string;
	menus: OrderMenu[];
	id: string;
	todo: string;
	isDone: boolean;
}

interface MenuState {
	category: string;
	menuId?: number;
	menuOptionList?: MenuOption[];
	menuPictureUrl?: string;
	name: string;
	popularity?: number;
	price: number;
	storeId: number;
}

interface OrderStatus {
	orderId: number;
	status: number;
}

interface MenuOption {
	content: string;
	option: string;
	price: number;
}
