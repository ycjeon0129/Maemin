import 'styled-components';

declare module 'styled-components' {
	export interface DefaultTheme {
		basicWidth: string;
		colors: {
			background: string;
			black: string;
			white: string;
		};
		transition: '0.3s';
		color: {
			main: string;
			sub: string;
			orange: string;
		};
	}
}
