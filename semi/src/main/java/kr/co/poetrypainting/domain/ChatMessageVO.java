package kr.co.poetrypainting.domain;

import lombok.Data;

import java.util.Date;

/**
 * packageName    : kr.co.poetrypainting.domain
 * fileName       : ChatMessageVO
 * author         : 함준혁
 * date           : 2023/04/12
 * description    : 채팅메시지VO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/12        함준혁       최초 생성
 */
@Data
public class ChatMessageVO {
    private Long no; //메시지 번호
    private Long chatRoomNo; // 채팅방번호
    private Long senderNo; // 발송자회원번호
    private String content; //메시지 내용
    private Long receiverNo; // 수신자회원번호
    private Date sendTime; //보낸 시간
    private boolean checked = false; // 수신 여부
    private String flag; // 채팅 위치 flag
}
