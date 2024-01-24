package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.ChatMessageVO;

import java.util.List;
/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : ChatMessageService
 * author         : 함준혁
 * date           : 2023/04/12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/12        함준혁            최초 생성
 */
public interface ChatMessageService {
    // 채팅 기록 저장
    int register(ChatMessageVO vo);
    // 채팅 내역 조회
    List<ChatMessageVO> getList(Long senderNo, Long chatRoomNo);
    // 채팅 수신내역 업데이트
    int checkedUpdate(Long senderNo, Long chatRoomNo);

}
