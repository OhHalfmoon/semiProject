package kr.co.poetrypainting.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/*")
@Data
@AllArgsConstructor
@Log4j
public class AddressController {
	private AddressService addressService;
	
	@GetMapping("/address")
    public String address(HttpSession session, Model model) {
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");      
        if(loginMember == null){
            return "redirect:/member/login";
        }
        
        return "member/address";

    }

	

}
