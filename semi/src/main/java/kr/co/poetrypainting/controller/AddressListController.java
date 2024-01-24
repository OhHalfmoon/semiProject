package kr.co.poetrypainting.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.TradeVO;
import kr.co.poetrypainting.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("addr")
@Data
@AllArgsConstructor
@Log4j
public class AddressListController {
	private AddressService addressService;
	

	
	@GetMapping("/list/{memberNo}")
	public List<AddressVO> getList(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		Long memberNo = loginMember.getMemberNo();
		log.warn(memberNo);
		return addressService.getAddressList(memberNo);
	}
	
	@PostMapping("/new")
	public Long create(@RequestBody AddressVO vo) {
		log.warn(vo);
		addressService.register(vo);;
		return vo.getAddrNo();
	}
	
	@PutMapping("/{addrNo}")
	public int modifyMain(@RequestBody AddressVO vo) {
			
		return addressService.modifyMain(vo);		
	}
	
	@PutMapping("/up/{addrNo}")
	public boolean modify(@RequestBody AddressVO vo) {
			
		return addressService.modify(vo);		
	}

	@DeleteMapping("/del/{addrNo}")
	public int remove(@PathVariable Long addrNo) {
			
		return addressService.remove(addrNo);		
	}	
	

}
