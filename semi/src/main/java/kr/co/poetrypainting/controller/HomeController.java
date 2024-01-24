package kr.co.poetrypainting.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.poetrypainting.domain.dto.Criteria;
import kr.co.poetrypainting.mapper.AddressMapper;
import kr.co.poetrypainting.service.BoardService;
import kr.co.poetrypainting.service.CheatService;
import kr.co.poetrypainting.service.ReplyService;
import kr.co.poetrypainting.service.TradeService;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@Data
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final BoardService boardService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("list", boardService.listMain());
		return "index";
	}

}
