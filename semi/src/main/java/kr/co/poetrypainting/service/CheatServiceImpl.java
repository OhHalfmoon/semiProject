package kr.co.poetrypainting.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import kr.co.poetrypainting.domain.*;
import kr.co.poetrypainting.mapper.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.poetrypainting.domain.dto.CheatCategoryDTO;
import kr.co.poetrypainting.domain.dto.Criteria;
import kr.co.poetrypainting.domain.dto.PenaltyDTO;
import kr.co.poetrypainting.domain.dto.WithDrawDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Log4j
public class CheatServiceImpl implements CheatService {

    @Autowired
    private CheatMapper cheatMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private MemberService memberService;


    @Override
    public void cheat(CheatVO vo) {
        //신고당했다고 쪽지발송
        cheatMapper.insert(vo);
    }

    /**
     * @param vo
     * @param receiverNo
     * @param message    메시지 재 발송 시 ( 채팅방 존재함 )
     * @author 함준혁
     */
    @Override
    public void sendMessage(CheatVO vo, Long receiverNo, String message) {
        // 채팅방 존재시 ( 이미 보낸 이력 있음 )
        ChatMessageVO chatMessageVO = new ChatMessageVO();

        List<ChatRoomVO> chatRoomVOList = chatMapper.getChatRoomList(16L);

        for (int i = 0; i < chatRoomVOList.size(); i++) {
            if (Objects.equals(chatMapper.getChatMessageList(16L, chatRoomVOList.get(i).getChatRoomNo()).get(i).getReceiverNo(), vo.getPenaltyUserNo())) {
                chatMessageVO.setChatRoomNo(chatRoomVOList.get(i).getChatRoomNo());
            }
        }
        // 채팅방 번호 지정
        chatMessageVO.setSenderNo(16L); // 발신자 지정 ( 관리자 ) 16번은 관리자 아이디 번호
        chatMessageVO.setContent(message); //최초 메시지 생성
        chatMessageVO.setReceiverNo(receiverNo); // 글작성자의 회원번호를 통해 수신자 지정

        chatMapper.insertMes(chatMessageVO);
    }

    /**
     * @param vo
     * @param receiverNo
     * @param message    메시지 첫 발송 ( 채팅방 생성 )
     * @author 함준혁
     */
    @Override
    public void firstSendMessage(CheatVO vo, Long receiverNo, String message) {
        ChatRoomVO chatRoomVO = new ChatRoomVO(); // 생성할 채팅방의 vo

        chatRoomVO.setName("상추마켓님께서 보낸 메시지 입니다."); // 상추마켓 메시지

        chatRoomVO.setBno(vo.getBno()); // 채팅방에 글번호를 설정합니다

        chatMapper.insertRoom(chatRoomVO); // 채팅방 생성

        ChatMessageVO chatMessageVO = new ChatMessageVO();

        List<ChatRoomVO> allChatRoom = chatMapper.getList();

        chatMessageVO.setChatRoomNo(allChatRoom.get(allChatRoom.size() - 1).getChatRoomNo()); // 채팅방 번호 지정
        chatMessageVO.setSenderNo(16L); // 발신자 지정 ( 관리자 ) 16번은 관리자 아이디 번호
        chatMessageVO.setContent(message); //최초 메시지 생성
        chatMessageVO.setReceiverNo(receiverNo); // 글작성자의 회원번호를 통해 수신자 지정

        chatMapper.insertMes(chatMessageVO); // 메시지 발송
    }

    @Override
    public void penalty(CheatVO vo) {
        cheatMapper.update(vo);

        vo = cheatMapper.selectOne(vo.getChNo());

        MemberVO memberVO = memberMapper.get((long) vo.getPenaltyUserNo()); // 신고당한 사람의 회원 정보 입력

        boolean isExistRoom = false; // 채팅방 존재 여부 기본값 미존재

        int credit = memberVO.getCredit(); // 신용점수 미리 담아놓기

        List<ChatRoomVO> chatRoomVOList = chatMapper.getChatRoomList(16L); // 관리자가 가지고 있는 채팅방 목록 가져오기

        Long receiverNo = vo.getPenaltyUserNo(); // 게시글을 통한 수신자 번호 가져오기

        for (int i = 0; i < chatRoomVOList.size(); i++) {
            if (Objects.equals(chatMapper.getChatMessageList(16L, chatRoomVOList.get(i).getChatRoomNo()).get(i).getReceiverNo(), vo.getPenaltyUserNo())) {
                isExistRoom = true;
            }
        }

        switch (vo.getPenaltyNo()) {
            case 1:
                if (isExistRoom) {
                    sendMessage(vo, receiverNo, "[" + vo.getPenaltyEtc() + "]" + "사유로" + vo.getTitle() + "글이 반려되었습니다.");
                } else {
                    firstSendMessage(vo, receiverNo, "[" + vo.getPenaltyEtc() + "]" + "사유로" + vo.getTitle() + "글이 반려되었습니다.");
                }
                //반려됐다고 쪽지발송
                break;
            case 2:
                //경고먹었다고 점수 3점 까져서 지금 몇점입니다 쪽지발송
                memberMapper.decreaseCredit(vo.getPenaltyUserNo(), 3); //점수 차감
                if (isExistRoom) {
                    sendMessage(vo, receiverNo, vo.getTitle() + "에 대하여 [" + vo.getPenaltyEtc() + "]사유로 원래점수" + credit + "점 에서" + "3점이 차감되어" + "현재점수 : " + (credit - 3) + "점 입니다");
                } else {
                    firstSendMessage(vo, receiverNo, vo.getTitle() + "에 대하여 [" +vo.getPenaltyEtc() + "]사유로 원래점수" + credit + "점 에서" + "3점이 차감되어" + "현재점수 : " + (credit - 3) + "점 입니다");
                }
                log.warn(vo);
                break;
            case 3:
                //글 비공개 먹었다고 점수 5점 까져서 지금 몇점입니다 쪽지발송
                memberMapper.decreaseCredit(vo.getPenaltyUserNo(), 5);
                if (isExistRoom) {
                    sendMessage(vo, receiverNo, vo.getTitle() + "에 대하여 [" + vo.getPenaltyEtc() + "]사유로 글이 비공개되었습니다.");
                    sendMessage(vo, receiverNo, "원래점수" + credit + "점 에서" + "5점이 차감되어" + "현재점수 : " + (credit - 5) + "점 입니다");
                } else {
                    firstSendMessage(vo, receiverNo, vo.getTitle() + "에 대하여 [" + vo.getPenaltyEtc() + "]사유로 글이 비공개되었습니다.");;
                    firstSendMessage(vo, receiverNo, "원래점수" + credit + "점 에서" + "5점이 차감되어" + "현재점수 : " + (credit - 5) + "점 입니다");
                }
                boardMapper.blind(vo.getBno());
                break;
            case 4:
            	memberService.withdraw(new WithDrawDTO(memberMapper.get(vo.getPenaltyUserNo()), "서비스 규칙 위반으로 인한 관리자권한 계정삭제"));
                //계정정지메서드 필요
                break;
          

            default:
                break;
        }
    }

    @Override
    public CheatVO read(Long chNo) {

        return cheatMapper.selectOne(chNo);
    }

    @Override
    public List<Map<String, Object>> cheatList(Criteria cri) {

        return cheatMapper.selectCheat(cri);
    }

    @Override
    public List<Map<String, Object>> penaltyList(Criteria cri) {

        return cheatMapper.selectPenalty(cri);
    }

    public int getTotalCnt() {
        return cheatMapper.totalCnt();
    }

    public int getTotalPenaltyCnt() {
        return cheatMapper.totalPenaltyCnt();
    }

    @Override
    public List<PenaltyDTO> penaltyNameList() {
        // TODO Auto-generated method stub
        return cheatMapper.penaltyList();
    }

    @Override
    public List<CheatCategoryDTO> cheatNameList() {
        // TODO Auto-generated method stub
        return cheatMapper.cheatList();
    }


}