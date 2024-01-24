package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.LoginDTO;
import kr.co.poetrypainting.domain.dto.WithDrawDTO;
import kr.co.poetrypainting.mapper.AddressMapper;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : MemberServiceImplTests
 * author         : 방한솔
 * date           : 2023/04/06
 * description    : 회원 서비스 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/06        방한솔       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceImplTests {
    @Autowired
    private MemberService memberService;

    /**
     * Bcrypt 테스트
     */
    @Test
    public void testBcrypt(){
        String pw = "1234";

        String hashpw = BCrypt.hashpw(pw, BCrypt.gensalt());

        log.info(hashpw);

        Assert.assertTrue(BCrypt.checkpw(pw, hashpw));
    }

    /**
     * 회원가입 테스트
     */
    @Test
    public void testJoin() {

        MemberVO joinMember = MemberVO.builder()
                .memberId("test12344321")
                .password("1234")
                .nickname("서비스테스트4412341")
                .email("test4@test.com")
                .build();

        AddressVO vo = new AddressVO();
//		vo.setMemberNo(2L);
        vo.setMemberNo(joinMember.getMemberNo());
        vo.setAddr("서울 구로구 디지털로 306");
        vo.setAddrDetail("대륭포스트타워 2차 203호 더조은컴퓨터아카데미학원 구로점");
        vo.setY(37.4859231683292);
        vo.setX(126.897336395657);
        vo.setDong("구로동");

        int result = memberService.register(joinMember, vo);

        assertEquals(1, result);
    }

    /**
     * 로그인 테스트
     */
    @Test
    public void testLogin() {
        LoginDTO inputMember = LoginDTO.builder()
                .memberId("test3")
                .password("1234")
                .build();
        /*
        MemberVO inputMember = MemberVO.builder()
                .memberId(loginDto.getMemberId())
                .password(loginDto.getPassword())
                .build();
*/
        log.info(inputMember);

        MemberVO loginMember = memberService.login(inputMember);

        assertNotNull(loginMember);
    }

    @Test
    public void testWithdrawal(){

        MemberVO memberVO = memberService.searchMember(37L);


        WithDrawDTO withDrawDTO = new WithDrawDTO(memberVO, "테스트 이유");

        int withdraw = memberService.withdraw(withDrawDTO);


    }
}
