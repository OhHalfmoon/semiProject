package kr.co.poetrypainting.service;

import java.util.List;

import kr.co.poetrypainting.domain.TradeVO;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : TradeService
 * author         :	오상현
 * date           : 2023/04/13
 * description    : TradeService
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        오상현           거래 관련 CRUD. 물건번호를 통한 조회기능
 * 
 * 
 */
public interface TradeService {
	//내 전체 거래내역 조회
	List<TradeVO>getList(Long memberNo);
	
	//내 거래내역 특정 거래 조회
	List<TradeVO>getMyList(Long bno, Long memberNo);
	
	//해당 회원의 전체 거래신청 조회
	List<TradeVO>getListBySeller(Long memberNo);
	
	//해당 회원의 전체 예약신청 조회
	List<TradeVO>getListByReserve(Long memberNo);
	
	//해당 회원의 전체 거래완료내역 조회
	List<TradeVO>getListByFinish(Long memberNo);	
	
	
	//내 거래진행상태 조회
	TradeVO getProgress(Long bno);
	
	TradeVO getProgress2(Long bno, Long memberNo);
	
	//거래내역 단일 조회
	TradeVO get(Long tradeNo);
		
	// 거래테이블 생성 (구매자가 구매희망 누를시)
	int register(TradeVO vo);
	
	//거래상태변경 (판매중 -> 예약중, 판매완료 : 판매자가 변경)	
	int modify(TradeVO vo);
		
	//물품번호를 대입하여 구매자 조회
	Long getBuyer(Long bno);
	//물품번호를 대입하여 판매자 조회
	Long getSeller(Long bno);
	
	TradeVO getMyList2(Long bno);
	
	int remove (Long tradeNo);

	int getPgCount(Long tradeNo);
}
