package kr.co.poetrypainting.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ReplyVO;
import kr.co.poetrypainting.domain.dto.AttachDTO;
import kr.co.poetrypainting.domain.dto.Criteria;
import kr.co.poetrypainting.domain.dto.PageDto;
import kr.co.poetrypainting.mapper.AddressMapper;
import kr.co.poetrypainting.mapper.BoardMapper;
import kr.co.poetrypainting.service.BoardService;
import kr.co.poetrypainting.service.CheatService;
import kr.co.poetrypainting.service.MemberService;
import kr.co.poetrypainting.service.ReplyService;
import kr.co.poetrypainting.service.TradeService;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * 
* @packageName    : kr.co.poetrypainting.controller
* @fileName        : BoardController.java
* @author        : 이시화
* @date            : 2023.04.09
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.04.09        이시화       최초 생성
 */
@Controller
@Log4j
@RequestMapping("/board/*")
@Data
public class BoardController {
	private final BoardService boardService;
	private final ReplyService replyService;
	private final AddressMapper addressMapper;
	private final TradeService tradeService;
	private final CheatService cheatService;
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 목록 리스트 출력 
	 * @param : list<BoardVo>
	 *
	 * @date : 2023. 4. 10.
	 */
	@GetMapping("/list")
	public void list(Model model,Criteria cri){
		model.addAttribute("list", boardService.getListWhitAll(cri));
		model.addAttribute("page", new PageDto(boardService.getTotalCnt(), cri));
	}
	
	
	@GetMapping("/listaddr")
	public String listAddr(Model model,Criteria cri,HttpSession session, Integer option){
		
		MemberVO member = (MemberVO)session.getAttribute("loginMember");
		if(member == null){
			
			return  "redirect:/member/login";
		}
		if (option == null) {
			option = 1;
		}
		model.addAttribute("list", boardService.getListWithAddr(cri,member,option));
		model.addAttribute("page", new PageDto(boardService.getAddrCnt(member,option), cri));
		return "/board/listaddr";
	}
	


	@GetMapping("/category/{cateNo}")
	public String listCategory(@PathVariable int cateNo, Model model,Criteria cri,HttpSession session, Integer option){
		
		MemberVO member = (MemberVO)session.getAttribute("loginMember");
		if(member == null){
			
			return  "redirect:/member/login";
		}
		if (option == null) {
			option = 1;
		}
		model.addAttribute("category",boardService.getCategoryName(cateNo));
		model.addAttribute("list", boardService.getListCategory(cri, member, cateNo,option));
		model.addAttribute("page", new PageDto(boardService.getCateCnt(member, cateNo,option), cri));
		return "/board/listcategory";
	}
	

	/**
	 * 
	 * @author : 이시화
	 * @todo : url에서 글번호로만 조회 
	 * @param : bno
	 * @return : board/get으로
	 *
	 * @date : 2023. 4. 13.
	 */
	@GetMapping("{bno}")
	public String getByPath(Model model, @PathVariable Long bno, HttpSession session){
		MemberVO member = (MemberVO) session.getAttribute("loginMember");
		model.addAttribute("board", boardService.get(bno));
		model.addAttribute("pg", tradeService.getProgress(bno));
		model.addAttribute("cheatNameList", cheatService.cheatNameList());
		if (member != null) {
			model.addAttribute("trade", tradeService.getMyList(bno, member.getMemberNo()));
			model.addAttribute("trpg", tradeService.getProgress2(bno, member.getMemberNo()));
			model.addAttribute("member", member.getMemberNo());
		}else {
			model.addAttribute("member", null);
		}
		
		return "/board/get";
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글작성 
	 * @param : session에 있는 멤버값
	 * @return : board/write
	 *
	 * @date : 2023. 4. 13.
	 */
	@GetMapping("/write")
	public String write(Model model,HttpSession session){
		MemberVO member = (MemberVO)session.getAttribute("loginMember");
		if(member == null){
			
			return  "redirect:/member/login";
		}
		model.addAttribute("category",boardService.getCategoryList());
		model.addAttribute("addr",addressMapper.getMyList(member.getMemberNo()));
		return "/board/write";
			
		
		
	}
	
	@PostMapping("/write")
	public String write(BoardVo vo, HttpSession session){
		MemberVO member = (MemberVO)session.getAttribute("loginMember");
		
		vo.setMemberNo(member.getMemberNo());
	
		log.info(vo);
		boardService.register(vo);
		return "redirect:/board/list";

	}
	@GetMapping("/modify")
	public String modify(Long bno,Model model,HttpSession session){
		MemberVO member = (MemberVO)session.getAttribute("loginMember");
		if(member == null){
			
			return  "redirect:/member/login";
		}else if(!(member.getMemberNo() == boardService.get(bno).getMemberNo())){
			return "redirect:/";
		}
		
		model.addAttribute("category",boardService.getCategoryList());
		model.addAttribute("addr",addressMapper.getMyList(member.getMemberNo()));
		model.addAttribute("board", boardService.get(bno));
		return "/board/modify";
			
		
		
	}
	@PostMapping("/modify")
	public String modify(BoardVo vo) {

		// 원본 리스트
		List<AttachDTO> originList = boardService.get(vo.getBno()).getAttachs();
		Long bno = vo.getBno();
		// 수정 될 리스트
		List<AttachDTO> list = vo.getAttachs();

		log.info("modify");
		log.info(vo);
		boardService.modify(vo);
		
		return "redirect:/board/" + bno;

	}
	
	@PostMapping("/uptime")
	public String update(Long bno) {
	    boardService.uptime(boardService.get(bno));
	    return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno,HttpSession session){
		MemberVO member = (MemberVO)session.getAttribute("loginMember");
		
		if(member == null){
			
			return  "redirect:/member/login";
		}
		List<AttachDTO> list = boardService.get(bno).getAttachs();
		for (AttachDTO dto : list) {
			boardService.deleteFile(dto);
		}
		
		boardService.remove(bno);
		
		return "redirect:/";
	}
	

	@GetMapping("/search")
	public void search(Model model, Criteria cri){
		model.addAttribute("list",boardService.getListWithKeyword(cri));
		model.addAttribute("page", new PageDto(boardService.getTotalCntSearch(cri), cri));
		
		
	}



}
