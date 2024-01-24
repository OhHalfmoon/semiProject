package kr.co.poetrypainting.controller;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.ChatMessageVO;
import kr.co.poetrypainting.domain.ChatRoomVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.mapper.BoardMapper;
import kr.co.poetrypainting.mapper.ChatMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * packageName    : kr.co.poetrypainting.controller
 * fileName       : ChatController
 * author         : 함준혁
 * date           : 2023/04/18
 * description    : 채팅 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/18        함준혁            생성
 */
@Log4j
@Controller
@RequestMapping("room")
@AllArgsConstructor
public class ChatController {

    @Autowired
    private ChatMapper chatMapper; // ChatMapper 빈을 자동으로 주입
    @Autowired
    private BoardMapper boardMapper;

    /**
     * @param request HTTP 요청 객체
     * @param model   View에 전달할 데이터 모델
     * @return chat.jsp로 이동
     * @author 함준혁
     * Chat 화면 요청 처리를 위한 메서드
     */
    @GetMapping("/chat")
    public String chat(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/member/login"; // 로그인되지 않은 경우 로그인 페이지로 redirect
        }
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

        Long senderNo = loginMember.getMemberNo(); // 로그인한 사용자의 회원번호 가져오기

        List<ChatRoomVO> chatRooms = chatMapper.getChatRoomList(senderNo); // 로그인한 사용자가 속한 채팅방 목록 조회

        model.addAttribute("chatRooms", chatRooms);
        return "chat"; // chat.jsp로 이동
    }

    @GetMapping("/chat/{bno}")
    public String boardChat(HttpServletRequest request, Model model, @PathVariable Long bno) {
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/member/login"; // 로그인되지 않은 경우 로그인 페이지로 redirect
        }

        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); //세션을 통한 로그인회원가져오기


        Long senderNo = loginMember.getMemberNo(); // 로그인한 사용자의 회원번호 가져오기

        // 채팅방이 이미 있는지 없는지 구별이 필요함
        boolean isExistRoom = false; // 채팅방 존재 여부 기본값 미존재

        List<ChatRoomVO> allChatRoom = chatMapper.getList(); // 모든 채팅방 가져오기

        for (int i = 0; i < allChatRoom.size(); i++) { //채팅방  탐색
            if (Objects.equals(allChatRoom.get(i).getBno(), bno)) {
                isExistRoom = true;
            }
        }

        if (isExistRoom) {
            // 채팅방 존재시 ( 이미 보낸 이력 있음 )
            model.addAttribute("chatRooms", chatMapper.getChatRoomList(senderNo)); // 이미가지고있던 채팅방 목록
            return "redirect:/room/chat"; // chat.jsp 로 이동
        } else {
            // 채팅방 미 존재시 ( 최초 메시지 보내기 )
            BoardVo boardVo = boardMapper.read(bno); // 게시글을 통한 게시글 정보 가져오기

            ChatRoomVO chatRoomVO = new ChatRoomVO(); // 생성할 채팅방의 vo

            Long receiverNo = boardVo.getMemberNo(); // 게시글을 통한 수신자 번호 가져오기

            chatRoomVO.setName(boardVo.getTitle() + "::" + boardVo.getMemberName()); // 글제목 -> 채팅방제목 + 글작성자 이름

            chatRoomVO.setBno(bno); // 채팅방에 글번호를 설정합니다

            chatMapper.insertRoom(chatRoomVO); // 채팅방 생성

            ChatMessageVO chatMessageVO = new ChatMessageVO();

            allChatRoom = chatMapper.getList();

            chatMessageVO.setChatRoomNo(allChatRoom.get(allChatRoom.size() - 1).getChatRoomNo()); // 채팅방 번호 지정
            chatMessageVO.setSenderNo(senderNo); // 발신자 지정
            chatMessageVO.setContent(loginMember.getNickname() + " 님이 " + boardVo.getTitle() + " 글을 통해 대화를 시작했어요!"); //최초 메시지 생성
            chatMessageVO.setReceiverNo(receiverNo); // 글작성자의 회원번호를 통해 수신자 지정

            chatMapper.insertMes(chatMessageVO);

            model.addAttribute("chatRooms", chatMapper.getChatRoomList(senderNo));
            model.addAttribute("start", bno);
            return "redirect:/room/chat"; // chat.jsp로 이동
        }
    }

    /**
     * @param senderNo   메시지를 보내는 사용자의 회원번호
     * @param chatRoomNo 조회할 채팅방의 번호
     * @return 조회한 채팅방의 메시지 목록
     * @author 함준혁
     * 채팅방 내 메시지 목록 조회를 위한 메서드
     */
    @GetMapping("/chat/messages")
    @ResponseBody
    public List<ChatMessageVO> getChatMessages(@RequestParam Long senderNo, @RequestParam Long chatRoomNo) {
        chatMapper.checkedUpdate(senderNo, chatRoomNo); // 메시지가 조회되었음을 DB에 업데이트
        List<ChatMessageVO> chatMessages = chatMapper.getChatMessageList(senderNo, chatRoomNo); // 채팅방 내 메시지 목록 조회
        return chatMessages; // 조회한 메시지 목록 반환
    }

    /**
     * @param chatMessageVO 저장할 채팅 메시지 VO
     * @return ResponseEntity 객체를 반환
     * @author 함준혁
     * 채팅 메시지 저장을 위한 메서드
     */
    @PostMapping("/chat/save")
    @ResponseBody
    public ResponseEntity<String> saveChatMessage(@RequestBody ChatMessageVO chatMessageVO) {
        try {
            chatMapper.insertMes(chatMessageVO); // 채팅 메시지 DB에 저장
            return ResponseEntity.ok().build(); // 정상 처리 응답 반환
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // 에러 응답 반환
        }
    }
}

