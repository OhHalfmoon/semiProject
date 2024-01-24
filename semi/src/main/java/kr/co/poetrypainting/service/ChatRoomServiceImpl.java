package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.ChatRoomVO;
import kr.co.poetrypainting.mapper.ChatMapper;

import java.util.List;
/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : ChatRoomServiceImpl
 * author         : 함준혁
 * date           : 2023/04/12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/12        함준혁            최초 생성
 */
public class ChatRoomServiceImpl implements ChatRoomService{
    ChatMapper mapper;

    //채팅방 생성
    @Override
    public int register(ChatRoomVO vo) {
        return mapper.insertRoom(vo);
    }

    // 채팅방 목록 조회
    @Override
    public List<ChatRoomVO> getList(Long no) {
        return mapper.getChatRoomList(no);
    }

    // 채팅방 삭제 ( 나가기 )
    @Override
    public int delete(Long no) {
        return mapper.delete(no);
    }
}
