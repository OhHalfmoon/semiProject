package kr.co.poetrypainting.service;



import kr.co.poetrypainting.domain.ChatRoomVO;

import java.util.List;
/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : ChatRoomService
 * author         : 함준혁
 * date           : 2023/04/12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/12        함준혁            최초 생성
 */
public interface ChatRoomService {
    // create 성공시에 1을 반환
    // update 는 실행된 갯수 반환
    // delete 도 실행된 갯수 반환
    // read 반환받을 객체 자체

    // 채팅방 생성
    int register(ChatRoomVO vo);

    // 채팅방 조회
    List<ChatRoomVO> getList(Long no);

    // 채팅방 삭제
    int delete(Long no);



}
