import JSEncrypt from 'jsencrypt';
import store from '../store/store';
import { SHA256 } from 'crypto-js';
// TODO
// APP 처음 실행 시 GET 요청. RETURN으로 INDEX, PUBLIC KEY
// PUBLIC KEY로 데이터 암호화 하고, 암호화 값과 INDEX를 서버에 전송.

export const encrypt = (text: string) => {
	// RSA Key
	const secureState = store.getState().secure;
	const key = secureState.publicKey;

	// RSA 세팅
	const encrypt = new JSEncrypt();
	encrypt.setKey(key);

	const plainText = text;
	const encryptedText = encrypt.encrypt(plainText);

	return encryptedText.toString();
};

export const SHA = (text: string) => {
	console.log(SHA256(text).toString());
	return SHA256(text).toString();
};
