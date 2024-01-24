package kr.co.poetrypainting.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.domain.TradeVO;
import lombok.extern.log4j.Log4j;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : TradeServiceTests
 * author         :	오상현
 * date           : 2023/04/13
 * description    : TradeServiceTest
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        오상현           거래 관련 CRUD. 물건번호를 통한 조회기능
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TradeServiceTests {
	@Autowired
	private TradeService tradeService;
	
	@Test
	public void testExist() {
		assertNotNull(tradeService);
		log.warn(tradeService);
	}
	
	@Test
	public void testGetList() {
		Long memberNo = 15L;
		log.warn(tradeService.getList(memberNo));		
	}
	
	@Test
	public void testGetMyList() {
		Long bno = 5L;
		Long memberNo = 13L;
		log.warn(tradeService.getMyList(bno, memberNo));		
	}
	
	@Test
	public void testGet() {
		Long tradeNo = 1L;
		log.warn(tradeService.get(tradeNo));
	}
	
	@Test
	public void testRegister() {
		TradeVO tradeVO = new TradeVO();
		tradeVO.setMemberNo(13L);
		tradeVO.setBno(6L);
		tradeService.register(tradeVO);
		log.warn(tradeVO);
	}
	
	@Test
	public void testModify() {
		TradeVO vo = tradeService.get(3L);
		vo.setProgress(3L);
		tradeService.modify(vo);
		log.warn(vo);
	}
	
	@Test
	public void testGetBuyer() {
		Long bno = 6L;
		tradeService.getBuyer(bno);
	}
	
	@Test
	public void testGetSeller() {
		Long bno = 6L;
		tradeService.getSeller(bno);
	}
	@Test
	public void testGetListBySeller() {
		Long memberNo = 9L;
		tradeService.getListBySeller(memberNo);
	}

}
