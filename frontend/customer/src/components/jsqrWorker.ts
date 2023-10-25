import JsqrScanner from './jsqrScanner';

onmessage = async (e) => {
	console.log('Inside JsQr worker');
	const res = await JsqrScanner(e.data);
	console.log('Returned to JsQr worker');
	postMessage(res);
};
