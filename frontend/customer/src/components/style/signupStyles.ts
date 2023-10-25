import styled from 'styled-components';

export const SlideContainer = styled.div<{ step: number }>`
	display: flex;
	flex-direction: column;
	transition: transform 0.5s ease-in-out;
	transform: translateY(-${(props) => props.step * 25}%);
`;

export const StepWrapper = styled.div`
	width: 100%;
	height: 100vh;
	overflow: hidden;
`;
