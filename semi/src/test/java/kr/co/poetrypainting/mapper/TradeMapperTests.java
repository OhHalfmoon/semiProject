package kr.co.poetrypainting.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.TradeVO;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TradeMapperTests {
	@Autowired
	private TradeMapper tradeMapper;

	
	//내 전체 거래내역 조회
	@Test
	public void testExist() {
		assertNotNull(tradeMapper);
	}
	
	@Test
	public void tsetGetList() {
		tradeMapper.getList(9L);
	}
	
	@Test
	public void testGetMyList() {
		List<TradeVO> list = tradeMapper.getMyList(4L, 15L);
	}
	
	@Test
	public void testGetProgress() {
		TradeVO list = tradeMapper.getProgress(4L);
		log.warn(list);
	}
	
	@Test
	public void testGetProgress2() {
		TradeVO list = tradeMapper.getProgress2(100L, 13L);
		log.warn(list);
	}
	
	@Test
	public void testGetListBySeller() {
		List<TradeVO> list = tradeMapper.getListBySeller(9L);
		log.warn(list);
	}
	
	

	@Test
	public void testRead() {
		Long tradeNo = 1L;
		log.warn(tradeMapper.read(tradeNo));
	}
	
	@Test
	public void testInsert() {
		TradeVO tradeVO = new TradeVO();
		
		tradeVO.setMemberNo(13L);
		tradeVO.setBno(7L);
		tradeMapper.insert(tradeVO);
		log.warn(tradeVO);
	}
	
	@Test
	public void testUpdate() {
		TradeVO tradeVO = tradeMapper.read(4L);
		tradeVO.setProgress(3L);
		tradeMapper.update(tradeVO);
		log.warn(tradeVO);
	}
	
	@Test
	public void testGetBuyer() {
		Long bno = 4L;
		tradeMapper.getBuyer(bno);
	}
	
	@Test
	public void testGetSeller() {
		Long bno = 5L;
		tradeMapper.getSeller(bno);
	}
//	
//	//거래상태변경 (판매중 -> 예약중, 판매완료 : 판매자가 변경)	
//	int update(TradeVO vo);

	@Test
	public void testUpdateMemberFk(){
		log.warn(tradeMapper.getList(13L));

		tradeMapper.updateMemberFk(13L);

		log.warn(tradeMapper.getList(13L));
	}

}
