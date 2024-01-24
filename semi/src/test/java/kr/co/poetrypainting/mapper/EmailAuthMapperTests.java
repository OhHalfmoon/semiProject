package kr.co.poetrypainting.mapper;

import kr.co.poetrypainting.domain.EmailAuthVO;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * packageName    : kr.co.poetrypainting.mapper
 * fileName       : EmailAuthMapperTests
 * author         : banghansol
 * date           : 2023/04/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        banghansol       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class EmailAuthMapperTests {
    @Autowired
    private EmailAuthMapper mapper;

    @Test
    public void testExists(){
        log.info(mapper);
        assertNotNull(mapper);
    }

    @Test
    public void testInsert(){
        EmailAuthVO emailAuthVO = new EmailAuthVO();
        emailAuthVO.setEmail("test1@email.com");
        emailAuthVO.setCode("233421");

        mapper.insert(emailAuthVO);

        log.info(emailAuthVO.getAuthNo());
        assertNotNull(emailAuthVO.getAuthNo());
    }

    @Test
    public void testGetRecentOne(){
        String code = "543451";
        EmailAuthVO emailAuthVO = new EmailAuthVO();
        emailAuthVO.setEmail("test1@email.com");
        emailAuthVO.setCode(code);

        mapper.insert(emailAuthVO);

        EmailAuthVO vo = mapper.getRecentOne("test1@email.com");

        log.info(vo);
        assertEquals(vo.getCode(), code);
    }

}
