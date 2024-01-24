package kr.co.poetrypainting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ReplyVO;
import kr.co.poetrypainting.domain.TradeVO;
import kr.co.poetrypainting.service.ReplyService;
import kr.co.poetrypainting.service.TradeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("replies")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	private ReplyService replyService;
	private TradeService tradeService;
	
	@GetMapping({"list/{bno}", "list/{bno}/{rno}"})

	public List<ReplyVO> getList(@PathVariable Long bno, @PathVariable(required=false) Optional<Long> rno) {

		
		log.warn(bno);
		log.warn(rno.isPresent() ? rno.get() : 0L);
		log.warn(rno.orElse(0L));
		log.warn(rno);
		TradeVO vo = tradeService.getMyList2(bno);
		log.warn("vo"+ vo);

		// 로그인한 회원 & 거래 조인해서 거래 내역이 있는지 여부 확인하는 쿼리
		// 리뷰랑 회원이랑 조인해서 리뷰내역이 있는지 여부 확인하는 쿼리
		

		return replyService.getList2(bno, rno.orElseGet(() -> 0L));
	}
	
	@PostMapping("new")
	public Long create(@RequestBody ReplyVO vo) {
		log.warn(vo);
		replyService.register(vo);
		return vo.getRno();
	}
	
	@GetMapping("{bno}")
	public ReplyVO get(@PathVariable Long bno) {
		log.warn(bno);
		return replyService.getBno(bno);
	}
	
@PutMapping("{bno}")
	public int modify(@PathVariable Long bno, @RequestBody ReplyVO vo) {
//		 vo = replyService.getBno(bno);
		log.warn(bno);
		log.warn(vo);
		return replyService.modify(vo);
		
	}
	
	
}


