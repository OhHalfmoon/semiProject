package kr.co.poetrypainting.mapper;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.ChatMessageVO;
import kr.co.poetrypainting.domain.ChatRoomVO;
import kr.co.poetrypainting.domain.MemberVO;
import lombok.RequiredArgsConstructor;
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
 * @fileName       : ChatMapperTests.java
 * @author         : 함준혁
 * @date           : 2023.04.12
 * @description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023.04.12        함준혁       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ChatMapperTests {
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testExist(){
        assertNotNull(chatMapper);
    }

    @Test
    public void testGetList(){
        chatMapper.getList();
    }
    @Test
    public void testInsertMes(){
        ChatMessageVO vo = new ChatMessageVO();
        MemberVO vo1 = memberMapper.get(13L);
        MemberVO vo2 = memberMapper.get(9L);

        vo.setChatRoomNo(1L);
        vo.setSenderNo(vo1.getMemberNo());
        vo.setContent("어떤일로연락을주셨죠?");
        vo.setReceiverNo(vo2.getMemberNo());

        chatMapper.insertMes(vo);
        System.out.println(vo);
    }

    @Test
    public void testGetChatMessageList(){
        chatMapper.getChatMessageList(13L, 1L);
    }
    @Test
    public void testInsertRoom(){
        ChatRoomVO dto = new ChatRoomVO();

        BoardVo vo = boardMapper.read(2L);
        dto.setName("채팅방생성테스트2");
        dto.setBno(vo.getBno());
        chatMapper.insertRoom(dto);
    }

    @Test
    public void testGetChatRoomList(){
        chatMapper.getChatRoomList(9L);
    }

    @Test
    public void testDelete(){
        chatMapper.delete(1L);
    }

    @Test
    public void testCheckedUpdate(){
        chatMapper.checkedUpdate(9L, 2L);
    }
}
