/* eslint-disable @typescript-eslint/no-explicit-any */

const { kakao } = window;

export const getLocationByAddress = async (address: string): Promise<LaMa> => {
	const geocoder = new kakao.maps.services.Geocoder();

	return await new Promise((resolve) => {
		geocoder.addressSearch(address, function (result: any[]) {
			resolve(new kakao.maps.LatLng(result[0].y, result[0].x));
		});
	});
};
