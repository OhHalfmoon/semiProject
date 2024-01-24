package kr.co.poetrypainting.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.domain.ReplyVO;
import lombok.extern.log4j.Log4j;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : ReplyServiceTests
 * author         :	오상현
 * date           : 2023/04/11
 * description    : ReplyServiceTests 
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/11       오상현           후기 CRUD 서비스 테스트
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTests {
	@Autowired
	private ReplyService replyService;
	
	@Test
	public void testExist() {
		assertNotNull(replyService);
		log.warn(replyService);
	}
	
	@Test
	public void testRegister() {
		ReplyVO vo = new ReplyVO();
		vo.setMemberNo(15L);
		vo.setBno(9L);
		vo.setComment("서비스테스트");
		vo.setRating(5);
		log.warn(vo);
		replyService.register(vo);
		log.warn(vo);
	}
	
	@Test
	public void testGet() {
		Long rno = 3L;
		ReplyVO vo = replyService.get(rno);
		log.warn(vo);
	}
	
	@Test
	public void testRemove() {
		Long rno = 8L;
		ReplyVO vo = replyService.get(rno);
		log.warn(vo);
		assertTrue(replyService.remove(rno));
		vo = replyService.get(rno);
		assertNull(vo);
	}
	@Test
	public void testUpdate() {
		long bno = 10L;
		ReplyVO vo = replyService.getBno(bno);
		log.warn(vo);
		
		vo.setComment("오늘 후기 끝내보자");
		
		replyService.modify(vo);
		vo = replyService.getBno(bno);
		log.warn(vo);
	}
	@Test
	public void testGetList() {
		Long memberNo = 9L;
		log.warn(replyService.getList(memberNo));
	}
	
	@Test
	public void testGetAddress() {
		Long bno = 4L;
		log.warn(replyService.getAddress(bno));
	}
	
	
	@Test
	public void testGetNickName() {
		Long bno = 4L;
		log.warn(replyService.getNickName(bno));
	}
	
	
}
