package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.ChatMessageVO;
import kr.co.poetrypainting.mapper.ChatMapper;

import java.util.List;
/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : ChatMessageServiceImpl
 * author         : 함준혁
 * date           : 2023/04/12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/12        함준혁            최초 생성
 */
public class ChatMessageServiceImpl implements ChatMessageService{
    ChatMapper mapper;

    // 채팅 메시지 기록 저장
    @Override
    public int register(ChatMessageVO vo) {
        return mapper.insertMes(vo);
    }

    // 채팅 메시지(상세)내역 조회
    @Override
    public List<ChatMessageVO> getList(Long senderNo, Long chatRoomNo) {
        return mapper.getChatMessageList(senderNo, chatRoomNo);
    }

    @Override
    public int checkedUpdate(Long senderNo, Long chatRoomNo) {
        return mapper.checkedUpdate(senderNo, chatRoomNo);
    }


}
