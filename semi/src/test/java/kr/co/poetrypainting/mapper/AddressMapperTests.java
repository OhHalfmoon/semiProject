package kr.co.poetrypainting.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.MemberVO;
import lombok.extern.log4j.Log4j;


/**
 * 
 * @author 오상현
 * @Since 2023.04.06 
 * DB 연결 확인, 생성, 조회, 단일조회, 삭제테스트 확인
 * 
 * @Since 2023.04.07 
 * update 테스트 확인
 * 
 * @Since 2023.04.10 
 * memberNo를 통한 addrNo 조회 테스트 확인
 * 
 * @Since 2023.04.11
 * memberNo를 통한 해당 회원의 addr조회 테스트 확인
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AddressMapperTests {
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testExist() {
		assertNotNull(addressMapper);
	}
	
	@Test
	public void testGetList() {
		addressMapper.getList().forEach(log::warn);
	}
	
	@Test
	public void testGetMyList() {
		 Long memberNo = 16L;
		addressMapper.getMyList(memberNo);
	}
	
	
	@Test
	public void testRead() {
		Long addrNo = 1L;
		log.warn(addressMapper.read(addrNo));
	}
	
	@Test
	public void testInsert() {
		AddressVO vo = new AddressVO();		
		MemberVO vo2 = memberMapper.get(6L);
//		vo.setMemberNo(2L);
		vo.setMemberNo(vo2.getMemberNo());
		vo.setAddr("서울 구로구 디지털로 306");
		vo.setAddrDetail("대륭포스트타워 2차 203호 더조은컴퓨터아카데미학원 구로점");
		vo.setY(37.4859231683292);
		vo.setX(126.897336395657);
		addressMapper.insert(vo);
		log.warn(vo);	
	}
	
	@Test
	public void testInsertSelectKey() {
		AddressVO vo = new AddressVO();		
		MemberVO vo2 = memberMapper.get(6L);
		
		vo.setMemberNo(6L);
		vo.setAddr("서울 구로구 디지털로34길 56");
		vo.setAddrDetail("구로소방서 공단119안전센터");
		vo.setY(37.484225084951);
		vo.setX(126.898432630772);
		addressMapper.insertSelectkey(vo);
		log.warn(vo);
	}
	
	@Test
	public void testDelete() {
		Long AddrNo = 3L;
		log.warn(addressMapper.read(AddrNo));
		log.warn(addressMapper.delete(AddrNo));
		log.warn(addressMapper.read(AddrNo));
	}
	
	@Test
	public void testUpdate() {
		AddressVO vo = addressMapper.read(4L);
		vo.setAddr("서울 구로구 경인로 430");
		vo.setAddrDetail("고척스카이돔");
		vo.setY(37.4982125677913);
		vo.setX(126.867088741096);
		
		log.warn(vo);
		addressMapper.update(vo);
		AddressVO vo2 = addressMapper.read(4L);
		log.warn(vo2);
	}
	
	@Test
	public void testReadByMember() {
		Long a = memberMapper.getByMemberId("test3").getMemberNo();
		log.warn(a);
		addressMapper.readByMember(a);
	}
	
	@Test
	public void testGetDistance() {
		Long memberNo = 22L;
		int option = 30;
		addressMapper.getDistance(memberNo, option);
	}

	@Test
	public void testUpdateMemberNo(){
		addressMapper.updateMemberNo(35L);

		log.warn(addressMapper.getMyList(35L));
	}

	
}
