package kr.co.poetrypainting.mapper;



import java.util.List;
import java.util.Map;

import kr.co.poetrypainting.domain.CheatVO;
import kr.co.poetrypainting.domain.dto.CheatCategoryDTO;
import kr.co.poetrypainting.domain.dto.Criteria;
import kr.co.poetrypainting.domain.dto.PenaltyDTO;
import org.apache.ibatis.annotations.Param;


public interface CheatMapper {

  // 신고 정보 저장
  void insert(CheatVO vo);

  // 신고 처리시 업데이트(패널티 적용시)
  void update(CheatVO vo);


  // 신고 정보 단일 조회
  CheatVO selectOne(Long chNo);

  // 신고 정보 접수목록 조회
  List<Map<String, Object>> selectCheat(Criteria cri);
  
  // 신고 처리된 목록 조회
  List<Map<String, Object>> selectPenalty(Criteria cri);
  
  int totalCnt();

  int totalPenaltyCnt();
  
  List<PenaltyDTO> penaltyList();
  
  List<CheatCategoryDTO> cheatList();

  int updateMemberFK(@Param("memberNo") Long memberNo);
}