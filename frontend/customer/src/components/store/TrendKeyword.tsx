import styled from 'styled-components';
import TagIcon from '../../assets/imgs/tag.svg';
//content로 변경해야함
interface TrendKeywordProps {
	content: string;
}
const TrendKeywordContainer = styled.div`
	position: relative;
	background-color: white;
	display: flex;
	flex-direction: column;
	margin-top: 10px;
`;

const ContentContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	margin: 10px 20px;
`;

const TrendName = styled.div`
	font-size: 24px;
	position: relative;
	margin-left: 10px;
`;

const TrendList = styled.div`
	font-size: 18px;
	position: relative;
	margin-left: 40px;
	margin-top: 10px;
	margin-bottom: 10px;
`;

const TrendKeyword = (props: TrendKeywordProps) => {
	return (
		<TrendKeywordContainer>
			<ContentContainer>
				<img src={TagIcon} alt="Tag" />
				<TrendName>가게소개</TrendName>
			</ContentContainer>
			<TrendList>{props.content}</TrendList>
		</TrendKeywordContainer>
	);
};

export default TrendKeyword;
