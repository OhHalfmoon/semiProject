package kr.co.poetrypainting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.poetrypainting.domain.TradeVO;
import kr.co.poetrypainting.mapper.TradeMapper;
import lombok.AllArgsConstructor;
/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : TradeServiceImpl
 * author         :	오상현
 * date           : 2023/04/13
 * description    : TradeServiceImpl
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        오상현           거래 관련 CRUD. 물건번호를 통한 조회기능
 * 
 * 
 */
@Service
@AllArgsConstructor
public class TradeServiceImpl implements TradeService{
	@Autowired
	private TradeMapper trademapper;
	
	@Override
	public List<TradeVO> getList(Long memberNo) {		
		return trademapper.getList(memberNo);
	}

	@Override
	public List<TradeVO> getMyList(Long bno, Long memberNo) {
		return trademapper.getMyList(bno, memberNo);
	}

	@Override
	public TradeVO get(Long tradeNo) {		
		return trademapper.read(tradeNo);
	}

	@Override
	public int register(TradeVO vo) {
		return trademapper.insert(vo);
		
	}

	@Override
	public int modify(TradeVO vo) {
		return trademapper.update(vo);
	}

	@Override
	public Long getBuyer(Long bno) {
		return trademapper.getBuyer(bno);
	}

	@Override
	public Long getSeller(Long bno) {
		return trademapper.getSeller(bno);
	}

	@Override
	public TradeVO getMyList2(Long bno) {
		return trademapper.getMyList2(bno);
	}

	@Override
	public TradeVO getProgress(Long bno) {
		return trademapper.getProgress(bno);
	}
	
	@Override
	public TradeVO getProgress2(Long bno, Long memberNo) {
		return trademapper.getProgress2(bno, memberNo);
	}

	@Override
	public List<TradeVO> getListBySeller(Long memberNo) {

		return trademapper.getListBySeller(memberNo);
	}
	
	@Override
	public List<TradeVO> getListByReserve(Long memberNo) {
		
		return trademapper.getListByReserve(memberNo);
	}

	@Override
	public List<TradeVO> getListByFinish(Long memberNo) {
		return trademapper.getListByFinish(memberNo);
	}	

	@Override
	public int remove(Long tradeNo) {		
		return trademapper.delete(tradeNo);
	}

	@Override
	public int getPgCount(Long memberNo) {
		return trademapper.getPgCount(memberNo);
	}

}
