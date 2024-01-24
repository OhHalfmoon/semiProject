package kr.co.poetrypainting.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.ReplyVO;
import lombok.extern.log4j.Log4j;

/**
 * packageName    : kr.co.poetrypainting.mapper
 * fileName       : ReplyMapperTests
 * author         :	오상현
 * date           : 2023/04/11
 * description    : ReplyMapperTest  
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/11        오상현           후기 CRUD test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void testExist() {
		assertNotNull(replyMapper);		
	}
	
	@Test
	public void testCreate() {
		ReplyVO replyVO = new ReplyVO();
		replyVO.setMemberNo(16L);
		replyVO.setBno(89L);
		replyVO.setComment("평점입력테스트");
		replyVO.setRating(5);
		replyMapper.insert(replyVO);
	}
	
	@Test
	public void testRead() {
		Long rno = 3L;
		log.warn(replyMapper.read(rno));
	}
	
	@Test
	public void testDelete() {
		Long rno = 8L;
		log.warn(replyMapper.delete(rno));
	}
	
	@Test
	public void testUpdate() {
		ReplyVO vo = replyMapper.readBno(10L);
		vo.setComment("수정 테스트");
		log.warn(replyMapper.update(vo));
	}
	
	@Test
	public void testGetList() {
		long a = memberMapper.getByMemberId("test4").getMemberNo();
		log.warn(a);
		log.warn(replyMapper.getList(a)); 
		
	}
	
	@Test
	public void testGetAddress() {
		long bno = 4;
		log.warn(replyMapper.getAddress(bno));
	}
	
	@Test
	public void testGetNickName() {
		long bno = 4;
		log.warn(replyMapper.getNickName(bno));
	}
	
	@Test
	public void testDeleteByBno(){
		replyMapper.deleteByBno(4L);
		log.info(replyMapper.readBno(4L));
	}

	@Test
	public void testReadBno(){
		log.info(replyMapper.readBno(209L));
	}

	@Test
	public void testUpdateMemberFK(){
		replyMapper.updateMemberFk(15L);
	}

	@Test
	public void testReplyMypage(){
		log.info(replyMapper.getListMypage(38L));
	}
}
