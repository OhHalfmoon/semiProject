package kr.co.poetrypainting.mapper;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.ChatMessageVO;
import kr.co.poetrypainting.domain.ChatRoomVO;
import kr.co.poetrypainting.domain.MemberVO;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @packageName    : kr.co.poetrypainting.mapper
 * @fileName       : ShopMapperTests.java
 * @author         : 함준혁
 * @date           : 2023.04.19
 * @description    : 상점매퍼 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023.04.19        함준혁       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ShopMapperTests {
    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void testExist(){
        assertNotNull(shopMapper);
    }

    @Test
    public void testInsertShop(){
        Long memberNo = 4L;
        String nickname = "안녕님";
        shopMapper.insertShop(memberNo, nickname);
    }

    @Test
    public void testUpdateName(){
        String shopName = "안녕상점";
        Long memberNo = 4L;
        shopMapper.updateName(shopName, memberNo);
    }
    @Test
    public void testUpdateIntro(){
        String intro = "안녕상점을 소개합니다";
        Long memberNo = 4L;
        shopMapper.updateIntro(intro, memberNo);
    }

    @Test
    public void testGetBoardList(){
        Long memberNo = 9L;
        shopMapper.getBoardList(memberNo);
    }

    @Test
    public void testUpdateVisitCnt(){
        Long memberNo = 4L;
        shopMapper.updateVisitCnt(memberNo);
    }

    @Test
    public void testGet(){
        Long memberNo = 4L;
        shopMapper.get(memberNo);
    }

}
