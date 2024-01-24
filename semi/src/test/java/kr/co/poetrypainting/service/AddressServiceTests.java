package kr.co.poetrypainting.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.mapper.MemberMapper;
import lombok.extern.log4j.Log4j;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : AddressServiceTests
 * author         :	오상현
 * date           : 2023/04/06
 * description    : AddressServiceTests 
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/06        오상현           register 테스트 성공
 * 2023/04/07        오상현           remove, update 테스트 성공
 * 2023.04.10		 오상현			  회원 id와 bno를 받아와 거리 측정 테스트
 * 2023.04.11		 오상현			  회원 id를 받아와 해당 회원의 주소록 확인
 * 2023.04.12		 오상현			  distance 수정
 * 
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AddressServiceTests {
	@Autowired
	private AddressService addressService;
	
	@Test
	public void testExist() {
		assertNotNull(addressService);
		log.warn(addressService);
	}
	
	@Test
	public void testRegister() {
		AddressVO vo = new AddressVO();
		vo.setMemberNo(16L);
		vo.setAddr("서울 구로구 항동 1-1");
		vo.setAddrDetail("성공회대학교");
		vo.setY(37.4875235978289);
		vo.setX(126.826163535057);
		
		log.warn(vo);
		addressService.register(vo);
		log.warn(vo);			
	}
	
	@Test
	public void testRemove() {
		Long addrNo = 23L;
		AddressVO vo = addressService.get(addrNo);
		log.warn(vo);
		addressService.remove(addrNo);
		vo = addressService.get(addrNo);
		assertNull(vo);
	}
	
	@Test
	public void testUpdate() {
		Long addrNo = 4L;
		AddressVO vo = addressService.get(addrNo);
		log.warn(vo);
		
		vo.setAddr("서울 구로구 경인로 430");
		vo.setAddrDetail("고척스카이돔");
		vo.setY(37.4982125677913);
		vo.setX(126.867088741096);
		
		addressService.modify(vo);
		vo = addressService.get(addrNo);
		
		log.warn(vo);
	}
	@Test
	public void testDistance() {
		Long myNo = 15L;
		Long no = 4L;
//		BoardVo boardVo = new BoardVo();
		log.warn(addressService.distance(myNo, no));		
	}
	@Test
	public void testGetMyAddress() {
		String id  = "test1";
		log.warn(addressService.getMyAddress(id));
	}
	
	@Test
	public void testGetDistance() {
				Long memberNo = 22L;
				int option = 20;
		log.warn(addressService.getDistance(memberNo, option));
	}
}
