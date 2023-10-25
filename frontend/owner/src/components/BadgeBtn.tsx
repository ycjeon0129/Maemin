import React, { useState } from 'react';
import cn from 'classnames';
import { MdAdd } from 'react-icons/md';
import { FcComboChart, FcBusinessman, FcBusiness, FcDownRight } from 'react-icons/fc';
import { BadgeContainer, BadgeLi } from './style/badgeBtn';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';
import { useNavigate } from 'react-router';

const BadgeBtn = () => {
	const [open, setOpen] = useState(false);
	const storeId = useSelector((state: RootState) => state.user.storeId);
	const mouseIn = () => setOpen(true);
	const mouseOut = () => setOpen(false);
	const navigate = useNavigate();

	const actions = [
		{ label: '매장분석', icon: <FcComboChart />, onClick: console.log },
		storeId && storeId > 0
			? { label: '내정보', icon: <FcBusinessman />, onClick: console.log }
			: {
					label: '로그인하기',
					icon: <FcDownRight />,
					onClick: () => {
						navigate('login');
					},
			  },
		{ label: '주문접수', icon: <FcBusiness />, onClick: console.log },
	];

	return (
		<BadgeContainer className="fab-container" onMouseEnter={mouseIn} onMouseLeave={mouseOut}>
			<BadgeLi className="fab-button">
				<MdAdd />
			</BadgeLi>
			{actions.map((action, index) => (
				<BadgeLi
					style={{ transitionDelay: `${index * 25}ms` }}
					// className={`fab-action ${open})`}
					className={cn('fab-action', { open })}
					key={action.label}
					onClick={action.onClick}
				>
					{action.icon}
					<span className="tooltip">{action.label}</span>
				</BadgeLi>
			))}
		</BadgeContainer>
	);
};

export default BadgeBtn;
