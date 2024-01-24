package kr.co.poetrypainting.service;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.domain.CheatVO;
import kr.co.poetrypainting.domain.dto.Criteria;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CheatServiceTests {
	
	@Autowired 
	private CheatService cheatService;
	
	@Test
	public void testExist(){
		assertNotNull(cheatService);
	}
//	@Test
//	public void testCheat(CheatVO vo){
//		cheatService.cheat(vo);
//	}
//	
//	@Test
//	public void testPenalty(CheatVO vo){
//		cheatService.penalty(vo);
//	}
	
//	@Test
//	public void testRead(Long chNo){
//		cheatService.read(11L);
//	}
	
	@Test
	public void testCheatList(){
		cheatService.cheatList(new Criteria());
	}
	
	@Test
	public void testPenaltyList(){
		log.info(cheatService.penaltyList(new Criteria()));
	}
	
}
