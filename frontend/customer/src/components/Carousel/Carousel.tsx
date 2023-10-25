import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import styled from 'styled-components';
import { CarouselTitle } from '../text';

const CarouselWrapper = styled.section<{ background: string | undefined }>`
	position: relative;
	background: ${(props) => (!props.background ? '#fff' : props.background)};
	width: 390px;
	margin: 15px 0;
	padding-bottom: 10px;
`;

interface SliderProps {
	children: React.ReactNode;
	keyword?: string;
	className?: string;
	autoplay?: boolean | number;
	speed?: number;
	loop?: boolean;
	slideToShow?: number;
	background?: string;
	dots?: boolean;
	storeDataLength?: number;
}

const Carousel = ({
	children,
	keyword,
	className,
	autoplay = true,
	speed = 300,
	loop = true,
	slideToShow = 2,
	background,
	dots,
	storeDataLength = 1,
}: SliderProps) => {
	const settings = {
		dots: dots || false,
		infinite: loop,
		speed: speed,
		slidesToShow: storeDataLength < slideToShow ? storeDataLength : slideToShow, // 수정
		autoplay: Boolean(autoplay),
		autoplaySpeed: typeof autoplay === 'boolean' ? 3000 : autoplay,
	};

	return (
		<CarouselWrapper className={className} background={background}>
			<CarouselTitle>{keyword ? `#${keyword}` : keyword}</CarouselTitle>
			<Slider {...settings}>{children}</Slider>
		</CarouselWrapper>
	);
};

export default Carousel;
