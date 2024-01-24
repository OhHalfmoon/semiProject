package kr.co.poetrypainting.service;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.controller.BoardControllerTests;
import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.dto.Criteria;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Autowired
	private BoardService boardService;
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 의존성 주입 테스트 
	 * @date : 2023. 4. 10.
	 */
	@Test
	public void testExist(){
		assertNotNull(boardService);
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 작성 테스트 
	 * @param :
	 * @return : 
	 *
	 * @date : 2023. 4. 10.
	 */
	@Test
	public void testRegister(){
		BoardVo vo = new BoardVo();
		vo.setTitle("서비스테스트1");
		vo.setContent("서비스테스트 내용1");
		vo.setPrice(99999L);
		vo.setMemberNo(9L);
		vo.setCateNo(1);
		boardService.register(vo);
		log.info(vo);
	}
	
	
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 삭제 테스트 
	 * @date : 2023. 4. 10.
	 */
	@Test
	public void testRemove(){
		log.info(boardService.get(297L));
		boardService.remove(297L);
		log.info(boardService.get(297L));
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 단일조회 테스트 
	 * @date : 2023. 4. 10.
	 */
	@Test
	public void testGet(){
		Long bno = 4L;
		log.info(boardService.get(bno));
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 수정 테스트 
	 * @param : Board
	 *
	 * @date : 2023. 4. 10.
	 */
	@Test
	public void testModify(){
		BoardVo vo = boardService.get(4L);
		vo.setTitle("서비스테스트3수정");
		vo.setContent("서비스테스트 내용3수정");
		vo.setPrice(99L);
		vo.setCateNo(1);
		boardService.modify(vo);
		log.info(boardService.get(4L));
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 목록 조회 테스트 
	 * @date : 2023. 4. 10.
	 */
	@Test
	public void testGetList(){
		boardService.getList().forEach(log::info);
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 카테고리 조회 
	 *
	 * @date : 2023. 4. 11.
	 */
	@Test
	public void testGetCategoryList(){
		boardService.getCategoryList().forEach(log::info);
	}
	
	@Test
	public void testGetListWhitAll(){
		boardService.getListWhitAll(new Criteria(1,24)).forEach(log::info);;
	};
	
	@Test
	public void testGetCategoryName(){
		boardService.getCategoryName(1);
	}
	@Test
	public void testGetListWithMain(){
		log.info(boardService.listMain());
	}
}
