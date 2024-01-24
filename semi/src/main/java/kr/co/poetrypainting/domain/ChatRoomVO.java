package kr.co.poetrypainting.domain;

import kr.co.poetrypainting.domain.BoardVo;
import lombok.Data;

import java.util.Date;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : ChatRoomDTO
 * author         : 함준혁
 * date           : 2023/04/12
 * description    : 채팅방DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/12        함준혁       최초 생성
 */
@Data
public class ChatRoomVO {
    private Long chatRoomNo; // 채팅방 번호
    private String name; // 채팅방 이름
    private Long bno; // 게시글 번호
    private Date regdate; // 채팅방생성일
    private String flag; // 읽었는지 안읽었는지.
}
