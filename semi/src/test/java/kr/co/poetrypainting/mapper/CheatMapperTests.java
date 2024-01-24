package kr.co.poetrypainting.mapper;

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
public class CheatMapperTests {
	
	@Autowired
	private CheatMapper cheatMapper;
	
	@Test
	public void testExist(){
		assertNotNull(cheatMapper);
	}
	@Test
	public void testInsert(){
		CheatVO vo = new CheatVO();
		vo.setMemberNo(9L);
		vo.setCateNo(2L);
		vo.setBno(303L);
		cheatMapper.insert(vo);
		log.info(cheatMapper.selectOne(303L));
	}
	
	@Test
	public void testSelectOne(){
		
		log.info(cheatMapper.selectOne(9L));
	}
	@Test
	public void testUpdate(){
		CheatVO vo = cheatMapper.selectOne(10L);
		vo.setPenaltyNo(2);
		vo.setPenaltyEtc("수정");
		cheatMapper.update(vo);
		log.info(cheatMapper.selectOne(10L));
	}
	@Test
	public void testSelectCheat(){
		
		log.info(cheatMapper.selectCheat(new Criteria()));
	}
	@Test
	public void testSelectPenalty(){
		
		log.info(cheatMapper.selectPenalty(new Criteria()));
	}
	
	@Test
	public void testPenaltyList(){
		
		log.info(cheatMapper.penaltyList());
	}

	@Test
	public void testUpdateMemberFK(){
		cheatMapper.updateMemberFK(23L);
	}

}
