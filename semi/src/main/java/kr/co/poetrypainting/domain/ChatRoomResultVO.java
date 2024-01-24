package kr.co.poetrypainting.domain;

import lombok.Data;

import java.util.Date;
/**
 * packageName    : kr.co.poetrypainting.domain
 * fileName       : ChatRoomResultVO
 * author         : 함준혁
 * date           : 2023/04/12
 * description    : 채팅메시지총결과VO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/12        함준혁       최초 생성
 */
@Data
public class ChatRoomResultVO {
    private Long chatRoomNo; // 채팅방번호
    private String name; // 채팅방 이름
    private Date regdate; // 채팅방 생성일
    private Long bno; // 게시글 번호
    private Long no; // 채팅방 번호
    private Long senderNo; // 발송자회원번호
    private String content; //메시지 내용
    private Date sendTime; //보낸 시간
    private boolean checked = false; // 수신 여부
    private Long receiverNo; // 수신자회원번호
    private String flag; // 채팅 위치 flag
}
