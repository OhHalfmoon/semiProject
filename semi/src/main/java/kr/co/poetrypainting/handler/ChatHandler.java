package kr.co.poetrypainting.handler;

import com.google.gson.Gson;
import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.ChatMessageVO;
import kr.co.poetrypainting.mapper.BoardMapper;
import kr.co.poetrypainting.mapper.ChatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * packageName    : kr.co.poetrypainting.handler
 * fileName       : ChatHandler
 * author         : 함준혁
 * date           : 2023/04/18
 * description    : 웹소켓채팅 핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/18        함준혁            완성
 */
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    // WebSocket 세션을 저장할 리스트
    List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

    // ChatMapper와 BoardMapper를 자동으로 주입받음
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private BoardMapper boardMapper;

    // WebSocket 연결이 성공하면 호출되는 메소드

    /**
     * @author 함준혁
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 세션 리스트에 현재 연결된 세션 추가
        sessions.add(session);
        // 현재 접속자 수와 접속자 정보 로그로 출력
        log.info("현재 접속자 수" + sessions.size());
        log.info("현재 접속자 정보");
    }

    // WebSocket에서 클라이언트로부터 메시지를 받으면 호출되는 메소드

    /**
     * @author 함준혁
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();

        // Gson을 사용하여 JSON 형태의 메시지 파싱
        Gson gson = new Gson();
        Map<?, ?> map = gson.fromJson(msg, Map.class);

        // 채팅 메시지 객체 생성
        ChatMessageVO vo = new ChatMessageVO();

        // 메시지에서 게시글 번호 파싱
        long bno = ((Double) map.get("bno")).longValue();
        BoardVo vo1 = boardMapper.read(bno);

        // 메시지에서 채팅방 번호, 보내는 사람 번호, 내용, 받는 사람 번호 파싱
        long chatRoomNo = ((Double) map.get("chatRoomNo")).longValue();
        long senderNo = ((Double) map.get("senderNo")).longValue();
        String content = (String) map.get("content");
        long receiverNo = ((Double) map.get("receiverNo")).longValue();

        // 채팅 메시지 객체에 정보 저장
        vo.setChatRoomNo(chatRoomNo);
        vo.setSenderNo(senderNo);
        vo.setContent((String) map.get("content"));

        // 받는 사람 번호가 0이면 게시글 작성자를 받는 사람으로 지정
        // 그렇지 않으면 메시지에서 받는 사람 번호로 지정
        if(receiverNo == 0){
            vo.setReceiverNo(vo1.getMemberNo());
        }else {
            vo.setReceiverNo(receiverNo);
        }
        // 연결된 모든 WebSocket 세션에 메시지 전송
        for(WebSocketSession s : sessions){
            s.sendMessage(new TextMessage(content + " 방금" + chatRoomNo + "," + senderNo));
        }
        // 채팅 메시지 DB에 저장
        chatMapper.insertMes(vo);
    }

    // WebSocket 연결이 종료되면 호출되는 메소드

    /**
     * @author 함준혁
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션 리스트에서 종료된 세션 제거
        sessions.remove(session);
        // 로그 출력
        log.info(session.getId() + "로그아웃");
    }
}

