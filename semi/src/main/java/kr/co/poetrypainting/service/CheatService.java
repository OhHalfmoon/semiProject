package kr.co.poetrypainting.service;

import java.util.List;
import java.util.Map;

import kr.co.poetrypainting.domain.CheatVO;
import kr.co.poetrypainting.domain.dto.CheatCategoryDTO;
import kr.co.poetrypainting.domain.dto.Criteria;
import kr.co.poetrypainting.domain.dto.PenaltyDTO;

public interface CheatService {

    void cheat(CheatVO vo);

    void penalty(CheatVO vo);

    CheatVO read(Long chNo);

    List<Map<String, Object>> cheatList(Criteria cri);

    List<Map<String, Object>> penaltyList(Criteria cri);

    int getTotalCnt();

    int getTotalPenaltyCnt();

    List<PenaltyDTO> penaltyNameList();

    List<CheatCategoryDTO> cheatNameList();

    void sendMessage(CheatVO vo, Long receiverNo, String message); // 메시지 발송

    void firstSendMessage(CheatVO vo, Long receiverNo, String message); // 처음 메시지 발송
}