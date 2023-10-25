import { createGlobalStyle, css } from 'styled-components';
import reset from 'styled-reset';

const GlobalStyle = createGlobalStyle`
	${reset}

	html,
	body {
		overflow: hidden;
	}
	button {
		background: "#FFF";
		font-size: 16px;
		color: "#999";
		:hover {
			cursor: pointer;
		}
	}
	* {
		box-sizing: border-box;
		-webkit-tap-highlight-color:transparent;
	}
	${({ theme }) => css`
		html {
			height: 100%;
			margin: 0 auto;
			body {
				display: flex;
				flex-direction: column;
				height: 100%;
				margin: 0;
				touch-action: pan-y;
				#root {
					background: ${theme.colors.background}; // theme 안에 color 중에서 background 를 선택
					color: ${theme.colors.black};
					display: flex;
					flex-direction: column;
					font-family: sans-serif;
					height: 100%;
				}
			}
		}
		::-webkit-scrollbar {
			display: none;
		}

		select {
			-webkit-appearance: none;
			-moz-appearance: none;
			appearance: none;
		}
		input {
			outline: none;
		}
		.dnd-container {
			height: 100%;
		}
	`}
	.fab-container {
		display: flex;
		list-style: none;
		margin: 0;
		padding: 0;
		flex-direction: column-reverse;
		position: fixed;
		right: 2em;
		bottom: 2em;
		max-height: 52px;

	&.open {
		max-height: max-content;
	}


	.fab-button {
		background-color: #00a8ff;

		svg {
			fill: white;
		}
	}

	.fab-action {
		transform: translateY(50px) scale(0);
		transition: transform 300ms, opacity 300ms;
		opacity: 0;

		&:hover {
			.tooltip {
			transform: translateX(-100%) scale(1);
			opacity: 1;
			}
		}

		&.open {
			transform: translateY(0) scale(1);
			opacity: 1;
		}

		.tooltip {
			padding: 4px 6px;
			font-size: 12px;
			position: absolute;
			left: -12px;
			transform: translateX(-75%);
			background-color: #353b48;
			border-radius: 4px;
			color: white;
			opacity: 0;
			transition: transform 300ms, opacity 300ms;
			user-select: none;
		}
	}
}
`;

export default GlobalStyle;
