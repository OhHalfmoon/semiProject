package kr.co.poetrypainting.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.CheatVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.Criteria;
import kr.co.poetrypainting.domain.dto.PageDto;
import kr.co.poetrypainting.mapper.AddressMapper;
import kr.co.poetrypainting.service.BoardService;
import kr.co.poetrypainting.service.CheatService;
import kr.co.poetrypainting.service.ReplyService;
import kr.co.poetrypainting.service.TradeService;
import lombok.Data;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cheat/*")
@Data
public class CheatController {

	private final CheatService cheatService;

	@GetMapping("/cheatList")
	public String cheatList(Model model, HttpSession session, Criteria cri){
		MemberVO admin = (MemberVO)session.getAttribute("loginMember");
		if (admin == null || !(admin.getAdmin())) {
			return  "redirect:/";
		}
		model.addAttribute("penaltyNameList", cheatService.penaltyNameList());
		model.addAttribute("page", new PageDto(cheatService.getTotalCnt(), cri));
		model.addAttribute("cheatList", cheatService.cheatList(cri));
		return "/cheat/cheatList";
		
		
	}
	@GetMapping("/penaltyList")
	public String penaltyList(Model model, HttpSession session, Criteria cri){
		MemberVO admin = (MemberVO)session.getAttribute("loginMember");
		if (!admin.getAdmin()) {
			return  "redirect:/";
		}
		model.addAttribute("penaltyNameList", cheatService.penaltyNameList());
		model.addAttribute("page", new PageDto(cheatService.getTotalPenaltyCnt(), cri));
		model.addAttribute("penaltyList", cheatService.penaltyList(cri));
		return "/cheat/penaltyList";
		
		
	}
	
	@PostMapping("/cheatList")
	public void penalty(CheatVO vo,Model model,Criteria cri){
		cheatService.penalty(vo);
		
		model.addAttribute("penaltyNameList", cheatService.penaltyNameList());
		model.addAttribute("page", new PageDto(cheatService.getTotalCnt(), cri));
		model.addAttribute("cheatList", cheatService.cheatList(cri));
		
	}
	@PostMapping("/report")
	public String report(CheatVO vo,Model model, Long bno,HttpSession session){
		MemberVO member = (MemberVO)session.getAttribute("loginMember");
		if(member == null){
			
			return  "redirect:/member/login";
		}
		vo.setBno(bno);
		vo.setMemberNo(member.getMemberNo());
		cheatService.cheat(vo);
		return "redirect:/board/" + bno;
	}
	
}
