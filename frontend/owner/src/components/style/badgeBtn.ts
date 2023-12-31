import styled from 'styled-components';

export const BadgeContainer = styled.ul`
	display: flex;
	// Display actions from bottom to top
	flex-direction: column-reverse;
	list-style: none;
	margin: 0;
	padding: 0;

	// Display button to the bottom right
	position: fixed;
	right: 2em;
	bottom: 2em;

	// Set max height to only trigger mouse enter
	// when user hover over first button
	max-height: 52px;

	&.open {
		// If open, container should fill the whole height
		max-height: max-content;
	}
`;

export const BadgeLi = styled.li`
	border-radius: 50%;
	box-shadow: 0 3px 6px lightgrey;
	display: grid;
	place-items: center;
	margin: 8px 0;
	font-size: 28px;
	padding: 12px;
	cursor: pointer;
	position: relative;
`;
