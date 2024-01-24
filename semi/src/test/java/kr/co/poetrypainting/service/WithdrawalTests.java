package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.WithdrawalVO;
import kr.co.poetrypainting.mapper.WithdrawalMapper;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : WithdrawalTests
 * author         : banghansol
 * date           : 2023/04/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/24        banghansol       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class WithdrawalTests {
    @Autowired
    private WithdrawalMapper withdrawalMapper;

    @Test
    public void testInsert(){
        WithdrawalVO vo = new WithdrawalVO();
        vo.setMemberId("test12345678");
        vo.setReason("테스트이유2");
        withdrawalMapper.insert(vo);
    }
}
