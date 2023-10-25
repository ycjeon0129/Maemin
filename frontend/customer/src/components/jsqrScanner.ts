import jsQR from 'jsqr';

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const JsqrScanner = (img: any) => {
	let res;
	console.log('Inside JsQr scanner');
	while (!res) {
		res = jsQR(img?.data, 320, 320);
	}
	return res.data;
};

export default JsqrScanner;
