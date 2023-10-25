import React from 'react';
import styled from 'styled-components';
import placeholderImage from '../../assets/imgs/subs-store.png';

interface StoreImageProps {
	imageUrl?: string;
	altDescription?: string;
}

const StyledImage = styled.img`
	width: 183px;
	height: 183px;
	object-fit: cover;
`;

const StoreImage = ({ imageUrl, altDescription }: StoreImageProps) => {
	return (
		<StyledImage
			src={imageUrl || placeholderImage}
			alt={altDescription || 'Store Image'}
			onError={(e) => {
				const target = e.target as HTMLImageElement;
				target.onerror = null;
				target.src = placeholderImage;
			}}
		/>
	);
};

export default StoreImage;
