package kr.co.poetrypainting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.poetrypainting.domain.TradeVO;
/**
 * packageName    : kr.co.poetrypainting.mapper
 * fileName       : TradeMapper
 * author         :	오상현
 * date           : 2023/04/13
 * description    : TradeMapper
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        오상현           거래 관련 CRUD. 물건번호를 통한 조회기능
 * 
 * 
 */
public interface TradeMapper {
	//내 전체 거래내역 조회
	List<TradeVO>getList(@Param("memberNo") Long memberNo);
	
	//내 거래내역 특정 거래 조회
	List<TradeVO>getMyList(@Param("bno") Long bno, @Param("memberNo") Long memberNo);
	
	//내 거래내역 특정 거래 조회
	TradeVO getProgress(@Param("bno") Long bno);
	
	TradeVO getProgress2(@Param("bno") Long bno, @Param("memberNo") Long memberNo);
	
	
	TradeVO getMyList2(@Param("bno") Long bno);
	
	List<TradeVO> getListBySeller(@Param("memberNo") Long memberNo);
	List<TradeVO> getListByReserve(@Param("memberNo") Long memberNo);
	List<TradeVO> getListByFinish(@Param("memberNo") Long memberNo);
		
	//거래내역 단일 조회
	TradeVO read(Long tradeNo);
		
	// 거래테이블 생성 (구매자가 구매희망 누를시)
	int insert(TradeVO vo);
	
	//거래상태변경 (판매중 -> 예약중, 판매완료 : 판매자가 변경)	
	int update(TradeVO vo);
	
	//물품번호를 대입하여 구매자 조회
	Long getBuyer(Long bno);
	//물품번호를 대입하여 판매자 조회
	Long getSeller(Long bno);
	
	//거래 요청 삭제
	int delete(Long tradeNo);

	int getPgCount(Long memberNo);

	int updateMemberFk(@Param("memberNo") Long memberNo);
	
}
