import React, { ChangeEvent } from 'react';

interface InputComponentProps {
	value?: string;
	name?: string;
	placeholder?: string;
	type?: string;
	onChange: (value: string) => void;
	backgroundColor?: string;
	fontSize?: string;
	margin?: string;
	borderRadius?: string;
	width?: string | number;
	height?: string | number;
	border?: string;
	paddingLeft?: string;
	padding?: string;
	inputRef?: React.ForwardedRef<HTMLInputElement>;
	max?: string;
	readOnly?: boolean;
}

const Input = React.forwardRef(
	({
		value,
		placeholder,
		type,
		onChange,
		backgroundColor,
		fontSize,
		margin,
		borderRadius,
		width,
		height,
		border,
		paddingLeft,
		padding,
		inputRef,
		name,
		max,
		readOnly,
	}: InputComponentProps): React.ReactElement => {
		const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
			onChange(e.target.value);
		};

		return (
			<input
				// autoFocus 맨 아래 input으로 커서이동됨 -> 회원가입 폼에 영향있어 지워둠
				ref={inputRef}
				type={type || 'text'}
				value={value}
				placeholder={placeholder}
				onChange={handleChange}
				name={name}
				max={max}
				readOnly={readOnly}
				style={{
					backgroundColor,
					fontSize,
					margin,
					borderRadius,
					width,
					height,
					border,
					padding,
					paddingLeft: paddingLeft ? paddingLeft : '10px',
				}}
			/>
		);
	},
);
Input.displayName = 'input';
export default Input;
