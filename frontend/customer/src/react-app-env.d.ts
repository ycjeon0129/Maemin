/// <reference types="react-scripts" />

interface IconTypeProps {
	width?: number;
	height?: number;
	color?: string;
}

type IconType = (props: IconTypeProps) => JSX.Element;

interface IconProps {
	iconSize?: number;
	icon: IconType;
}

interface CardProps {
	url?: string;
	width?: number;
	height?: number;
	title?: string;
	content?: string;
	titleSize?: string;
	onClick?: React.MouseEventHandler<HTMLElement>;
	count?: number;
	menus?: OrderMenu[];
}

type FontSizeProps = {
	fontSize?: string;
};

type SlideFlag = {
	active?: boolean;
};

interface SliderProps {
	/** 슬라이더 아이템 요소 */
	children: React.ReactNode;
	/** 커스텀 클래스 */
	className?: string;
	/** 자동재생 (속도 설정시 number 타입으로) */
	autoplay?: boolean | number;
	/** 슬라이더 속도 */
	speed?: number;
	/** 반복 여부 */
	loop?: boolean;
	keyword?: string;
	slideToShow?: number;
	background?: string;
	dots?: boolean;
}

interface CarouselProps {
	trendword: string;
}

interface TallProps {
	height?: string;
}

type HoverProps = {
	width?: number;
	height?: number;
};

interface SearchProps {
	placeholder?: string;
}

interface SelectProps {
	list?: string[];
	width?: string;
	onChange?: (selectOpt: string) => void;
	selectRef?: React.ForwardRef<HTMLSelectElement>;
}

interface NavigationProps {
	title?: string;
}

interface InputProps {
	width?: string;
}

interface locationState {
	lat: number | undefined;
	lng: number | undefined;
}

interface LaMa {
	La: number | undefined;
	Ma: number | undefined;
}

interface MenuData {
	menuId: number;
	storeId: number;
	category: number[];
	name: string;
	price: number;
	menuPictureUrl: string;
	popularity: number;
}

interface OrderMenu {
	menuId: number;
	name: string;
	price: number;
	quantity: number;
	menuOptions?: MenuOption[];
}

type MyOrder = {
	storeName: string;
	paymentMethod: string; // "TFT_PAY",
	totalPrice: number;
	requests: string;
	createdDate: Date;
	menuList: [];
};

type MenuOption = {
	option: string;
	price: number;
};

interface OrderData {
	orderId: number;
	storeId: number;
	userId: number;
	paymentMethod: number;
	totalPrice: number;
	requests: string;
	createdDate: string;
	menus: OrderMenu[];
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

interface booleanState {
	checker: boolean;
}
interface secureState {
	index: number;
	publicKey: string;
	validTime: string;
}
// 데이터 파싱용 props임. 나중에 삭제하면 됨.
interface storeProps2 {
	address_name: string;
	category_group_code: string;
	category_group_name: string;
	category_name: string;
	distance: string;
	id: number;
	phone: string;
	place_name: string;
	place_url: string;
	road_address_name: string;
	x: string;
	y: string;
}
interface storeProps {
	storeId: number;
	address_name: string;
	category_name: string;
	id: number;
	phone: string;
	name: string;
	place_url: string;
	road_address_name: string;
	latitude: string;
	longitude: string;
	content: string;
}
interface menuState {
	// menuOptionId
	menuId: number;
	menuName: string;
	menuPrice: number;
	menuCount: number; // quantity
	menuImg: string;
	menuPicker: string;
	menuPayerList: string[];
	index: number;
}
interface menuListProps {
	menuId: number;
	menuOptionId: number[];
	quantity: number;
}
