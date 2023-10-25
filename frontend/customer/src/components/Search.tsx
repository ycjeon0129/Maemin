import React, { useState } from 'react';
import Input from './Input';
import { SearchBox, SearchIconBox } from './style/search';
import { ReactComponent as SearchIcon } from '../assets/imgs/search.svg';
import { useNavigate } from 'react-router';
import { useDispatch } from 'react-redux';
import { openSearch } from '../store/searchSlice';

const Search = ({ placeholder }: SearchProps) => {
	const [val, setVal] = useState('');
	const navigate = useNavigate();
	const dispatch = useDispatch();

	return (
		<SearchBox
			onClick={() => {
				dispatch(openSearch());
				navigate('/search');
			}}
		>
			<Input
				value={val}
				placeholder={placeholder}
				onChange={setVal}
				height={40}
				width={305}
				border="none"
				borderRadius="10px"
			/>
			<SearchIconBox>
				<SearchIcon />
			</SearchIconBox>
		</SearchBox>
	);
};

export default Search;
