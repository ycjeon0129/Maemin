import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { MainRoutes } from './Router';
import Layout from './components/layout/Layout';

function App() {
	return (
		<Router basename="customer">
			<Layout>
				<MainRoutes />
			</Layout>
		</Router>
	);
}

export default App;
