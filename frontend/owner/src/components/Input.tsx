import React, { ChangeEvent } from 'react';

interface InputComponentProps {
	value: string;
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
	}: InputComponentProps): React.ReactElement => {
		const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
			onChange(e.target.value);
		};

		return (
			<input
				ref={inputRef}
				type={type ? type : 'text'}
				value={value}
				placeholder={placeholder}
				onChange={handleChange}
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
				autoComplete={type === 'password' ? 'off' : 'on'}
			/>
		);
	},
);
Input.displayName = 'input';
export default Input;
