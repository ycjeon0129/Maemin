import styled from 'styled-components';
import IconBox from '../IconBox';
import { ReactComponent as CategoryIcon } from '../../assets/imgs/categoryfoot.svg';
import { ReactComponent as HomeIcon } from '../../assets/imgs/home.svg';
import { ReactComponent as LogIcon } from '../../assets/imgs/log.svg';
import { ReactComponent as MyIcon } from '../../assets/imgs/mypage.svg';
import { useNavigate } from 'react-router';
import { useSelector } from 'react-redux';
import { RootState } from '../../store/store';

const FooterContainer = styled.footer`
	/* position: fixed; */
	height: 80px;
	min-width: 390px;
	margin-top: auto;
	background-color: white;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
	z-index: 999;
	@media (max-width: 768px) {
		position: relative;
		bottom: auto;
		/* 다른 모바일 스타일 속성 */
	}
`;

// const footerList = ['home', 'trend', 'log', 'myPage'];

const Footer = () => {
	const userName = useSelector((state: RootState) => state.user.userName);
	const navigate = useNavigate();
	const moveTo = (page: string) => {
		navigate(page);
	};

	return (
		<FooterContainer>
			<div onClick={() => moveTo('home')}>
				<IconBox icon={(props) => <HomeIcon {...props} />} iconSize={25} />
			</div>
			<div onClick={() => moveTo('trend')}>
				<IconBox icon={(props) => <CategoryIcon {...props} />} iconSize={35} />
			</div>
			<div onClick={() => moveTo('log')}>
				<IconBox icon={(props) => <LogIcon {...props} />} iconSize={25} />
			</div>
			<div
				onClick={() => {
					userName ? moveTo('mypage') : moveTo('login');
				}}
			>
				<IconBox icon={(props) => <MyIcon {...props} />} iconSize={25} />
			</div>
		</FooterContainer>
	);
};

export default Footer;
