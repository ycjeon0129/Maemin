import React from 'react';

interface TTT {
	bar: string;
}

function App() {
	const text = 'fdfdf';

	const test = 123;

	const foo: TTT = {
		bar: 'hi',
	};

	console.log(foo.bar);
	console.log(foo.bar);

	const a = (asdf: any) => {
		return asdf;
	};
	console.log(test);
	return <div className="App">hi</div>;
}

export default App;
