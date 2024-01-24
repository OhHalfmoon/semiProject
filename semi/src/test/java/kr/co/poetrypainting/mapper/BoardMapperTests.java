package kr.co.poetrypainting.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.controller.BoardControllerTests;
import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.Criteria;
import lombok.extern.log4j.Log4j;
/**
 * 
* @packageName    : kr.co.poetrypainting.mapper
* @fileName        : BoardMapperTests.java
* @author        : 이시화
* @date            : 2023.04.09
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.04.09        이시화       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private MemberMapper memeberMapper;
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 의존성 주입 확인 
	 * @date : 2023. 4. 9.
	 */
	@Test
	public void testExist(){
		assertNotNull(boardMapper);
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 목록조회 테스트 
	 * @date : 2023. 4. 9.
	 */
	@Test
	public void testGetList(){
		List<BoardVo> list = boardMapper.getList();
		list.forEach(log::info);
		
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 등록 테스트 
	 * @date : 2023. 4. 9.
	 */
	@Test
	public void testInsert(){
		BoardVo vo = new BoardVo();
		vo.setTitle("메퍼테스트1");
		vo.setContent("메퍼테스트 내용1");
		vo.setPrice(99999L);
		vo.setMemberNo(9L);
		vo.setCateNo(1);
		vo.setAddrNo(1L);
		boardMapper.insert(vo);
		log.info(vo);
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 삭제 테스트 
	 * @date : 2023. 4. 9.
	 */
	@Test
	public void testDelete(){
		Long bno = 280L;
		boardMapper.delete(bno);
		log.info(boardMapper.read(bno));
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 단일조회 테스트 
	 * @date : 2023. 4. 9.
	 */
	@Test
	public void testRead(){
		Long bno = 290L;
		log.info(boardMapper.read(bno));
	}
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 수정 테스트 
	 * @param : Board
	 *
	 * @date : 2023. 4. 9.
	 */
	@Test
	public void testUpdate(){
		BoardVo vo = boardMapper.read(3L);
		vo.setTitle("메퍼테스트3수정");
		vo.setContent("메퍼테스트 내용3수정");
		vo.setPrice(99L);
		vo.setCateNo(1);
		boardMapper.update(vo);
		log.info(boardMapper.read(3L));
	}
	

	@Test
	public void testGetCategory(){
		log.info(boardMapper.getCategory());
	}
	@Test
	public void testGetCategoryName(){
		log.info(boardMapper.getCategoryName(1));
	}
	
	@Test
	public void testGetListWhitName(){
		log.info(boardMapper.getListWithName(new Criteria(1,12)));
	}
	@Test
	public void testGetListMain(){
		log.info(boardMapper.getListMain());
	}
	
	@Test
	public void testGetListWhitKeyword(){
		String keyword = "아동";
		log.info(boardMapper.getListWithKeyword(new Criteria(1,12, keyword)));
	}
	@Test
	public void testGetTotalCnt(){
		log.info(boardMapper.getTotalCnt());
	}
	@Test
	public void testGetTotalCntSearch(){
		log.info(boardMapper.getTotalCntSearch(new Criteria(1,12, "아동")));
	}
	@Test
	public void testGetAddrCnt(){
		MemberVO member = memeberMapper.get(9L);
		log.info(boardMapper.getAddrCnt(member,3));
	}
	
	@Test
	public void testGetListWhitAddr(){
		   
		MemberVO member = memeberMapper.get(23L);
		log.info(member.getMemberNo());
		log.info(boardMapper.getListWithAddr(new Criteria(1,12),member, 5));
	}
	@Test
	public void testGetListCategory(){
		MemberVO member = memeberMapper.get(23L);
		log.info(boardMapper.getListCate(new Criteria(1,12), member,12 ,3));
	}
	@Test
	public void testGetCateCnt(){
		MemberVO member = memeberMapper.get(9L);
		log.info(boardMapper.getCateCnt(member,1,3));
	}
	@Test
	public void testUptime(){
		boardMapper.uptime(boardMapper.read(288L));
		log.info(boardMapper.getList());
	}

	@Test
	public void testUpdateMemberFK(){
		log.warn(boardMapper.getMyList(35L));

		boardMapper.updateMemberFK(35L);

		log.warn(boardMapper.getMyList(35L));
	}
}
