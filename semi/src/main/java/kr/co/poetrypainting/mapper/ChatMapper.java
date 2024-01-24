package kr.co.poetrypainting.mapper;

import kr.co.poetrypainting.domain.ChatMessageVO;
import kr.co.poetrypainting.domain.ChatRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : 함준혁
 * @packageName : kr.co.poetrypainting.mapper
 * @fileName : ChatMapper.java
 * @date : 2023.04.12
 * @description :  ChatMapper
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023.04.12        함준혁       최초 생성
 */
public interface ChatMapper {

    // 전체 채팅방 조회
    List<ChatRoomVO> getList();
    // 채팅 기록 저장
    int insertMes(ChatMessageVO vo);

    // 채팅 내역 조회
    List<ChatMessageVO> getChatMessageList(@Param("senderNo") Long senderNo, @Param("chatRoomNo") Long chatRoomNo);


    // 채팅방 생성
    int insertRoom(ChatRoomVO vo);

    // 채팅방 목록 조회
    List<ChatRoomVO> getChatRoomList(@Param("memberNo") Long memberNo);

    // 채팅방 삭제
    int delete(Long no);
    // 채팅 수신 내역 업데이트
    int checkedUpdate(@Param("senderNo") Long senderNo, @Param("chatRoomNo") Long chatRoomNo);

}
