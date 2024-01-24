package kr.co.poetrypainting.mapper;

import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.MemberModifyDto;
import kr.co.poetrypainting.domain.dto.MypageDTO;
import kr.co.poetrypainting.domain.dto.PassChangeDTO;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * packageName    : kr.co.poetrypainting.mapper
 * fileName       : MemberMapperTests
 * author         : 방한솔
 * date           : 2023/04/05
 * description    : 회원 Mapper 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/05        방한솔       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;

    /**
     * Bean 주입 테스트
     */
    @Test
    public void testExist(){
        assertNotNull(memberMapper);
    }

    /**
     * 입력받은 정보로 회원 등록을 한다.
     */
    @Test
    public void testInsert(){
        /*
        MemberVO vo = MemberVO.builder()
                .memberId("test2")
                .password("12345678")
                .nickname("테스트계정12")
                .email("test1@test.com")
                .build();

        int result = memberMapper.insert(vo);
*/
        //assertEquals(1, result);
    }

    /**
     * 입력받은 회원번호와 일치하는 회원이 있는지 조회한다.
     */
    @Test
    public void testGet() {
        MemberVO memberVO = memberMapper.get(1L);
        log.info(memberVO);
    }

    /**
     * 입력받은 회원 아이디와 일치하는 회원이 있는지 조회한다.
     */
    @Test
    public void testGetByMemberId() {
        MemberVO memberVO = memberMapper.getByMemberId("test");
        log.info(memberVO);
    }

    @Test
    public void testIdCheck() {
        String member = memberMapper.idCheck("test");
        log.info(member);
    }


    /**
     * 입력받은 회원 닉네임과 일치하는 회원이 있는지 조회한다.
     */
    @Test
    public void testGetByNickname() {
        MemberVO memberVO = memberMapper.getByNickname("테스트계정1");
        log.info(memberVO);
    }

    /**
     * 전체 회원리스트를 조회한다.
     */
    @Test
    public void testGetList() {
        List<MemberVO> list = memberMapper.getList();
        list.forEach(log::info);
    }

    /**
     * 입력받은 회원번호와 일치하는 회원의 회원정보를 수정한다.
     */
    @Test
    public void testUpdate(){
//        MemberModifyDto vo = MemberModifyDto.builder()
//                .memberNo(1L)
//                .nickname("수정닉네임123")
//                .build();

        MemberModifyDto vo = new MemberModifyDto();
        vo.setMemberNo(22L);
        vo.setNickname("수정닉네임123");
        vo.setEmail("test@test.com");

        int result = memberMapper.update(vo);

        assertEquals(1, result);
    }

    /**
     * 입력받은 회원번호와 일치하는 회원의 회원정보를 삭제한다.
     */
    @Test
    public void testDelete(){
        int result = memberMapper.delete(1L);

        assertEquals(1, result);
    }

    @Test
    public void passChange(){
        // $2a$10$9U75bCELJZHMuum2adcaWukEkt9MuUlZ8qej5JlEV5iLYhIfQ2Xp6
        PassChangeDTO passChangeDTO = new PassChangeDTO();
        passChangeDTO.setMemberNo(24L);
        passChangeDTO.setNewPassword("1234");

        memberMapper.changePassword(passChangeDTO);
    }

    @Test
    public void testGetMypage(){
        MypageDTO mypage = memberMapper.getMypage(9L);

        log.warn(mypage);
    }

    @Test
    public void testMemberCreditUpdate(){
        log.warn(memberMapper.get(9L));

        memberMapper.increaseCredit(9L, 15);

        log.warn(memberMapper.get(9L));

        memberMapper.decreaseCredit(9L, 10);

        log.warn(memberMapper.get(9L));
        memberMapper.setCredit(9L, 0);

        log.warn(memberMapper.get(9L));

    }
}
