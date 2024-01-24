package kr.co.poetrypainting.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ReplyVO;
import kr.co.poetrypainting.domain.TradeVO;
import kr.co.poetrypainting.service.TradeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("trades")
@RestController
@Log4j
@AllArgsConstructor
public class TradeController {
	private TradeService tradeService;
	
	@PostMapping("new")
	public Long create(@RequestBody TradeVO vo) {
		log.warn(vo);
		tradeService.register(vo);
		return vo.getTradeNo();
	}
	
//	@GetMapping("{bno}")
//	public TradeVO get(@PathVariable Long bno) {
//		log.warn(bno);
//		return tradeService.getMyList2(bno);
//	}
	
	@PutMapping("{tradeNo}")
	public int modify(@RequestBody TradeVO vo) {
	
		
		log.warn(vo);
		return tradeService.modify(vo);
		
	}
	
	//구매신청 조회
	@GetMapping({"/list/my/{memberNo}"})
	public List<TradeVO> getMyReserveList(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		Long memberNo = loginMember.getMemberNo();
		log.warn(memberNo);
		return tradeService.getList(memberNo);
	}

	//구매요청 삭제
	@DeleteMapping("my/{tradeNo}")
	public int Myremove(@PathVariable Long tradeNo) {
		log.warn(tradeNo);
		return tradeService.remove(tradeNo);
	}		
	

	//파는 물건 조회
	@GetMapping({"list/{memberNo}"})
	public List<TradeVO> getList(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		Long memberNo = loginMember.getMemberNo();
		log.warn(memberNo);
		return tradeService.getListBySeller(memberNo);
	}

	//예약조회
	@GetMapping({"list/reserve/{memberNo}"})
	public List<TradeVO> getReserveList(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		Long memberNo = loginMember.getMemberNo();
		log.warn(memberNo);
		return tradeService.getListByReserve(memberNo);
	}
	
	//거래완료조회
	@GetMapping({"list/finish/{memberNo}"})
	public List<TradeVO> getFinishList(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		Long memberNo = loginMember.getMemberNo();
		log.warn(memberNo);
		return tradeService.getListByFinish(memberNo);
	}
	
	//요청 삭제
	@DeleteMapping("{tradeNo}")
	public int remove(@PathVariable Long tradeNo) {
		log.warn(tradeNo);
		return tradeService.remove(tradeNo);
	}
	
	
	
	
	
}
