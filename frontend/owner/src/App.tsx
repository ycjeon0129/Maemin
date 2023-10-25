import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { MainRoutes } from './Router';
import Header from './components/Header';
import { Provider } from 'react-redux';
import store from './store/store';
import BadgeBtn from './components/BadgeBtn';

function App() {
	return (
		<Router basename="/owner">
			<Provider store={store}>
				<Header />
				<MainRoutes />
				<BadgeBtn />
			</Provider>
		</Router>
	);
}

export default App;
