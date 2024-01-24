package kr.co.poetrypainting.mapper;

import java.util.List;

import kr.co.poetrypainting.domain.dto.ReplyMypageDTO;
import org.apache.ibatis.annotations.Param;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ReplyVO;
import kr.co.poetrypainting.domain.TradeVO;

/**
 * packageName    : kr.co.poetrypainting.mapper
 * fileName       : ReplyMapper
 * author         :	오상현
 * date           : 2023/04/11
 * description    : ReplyMapper  
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/11        오상현           후기 CRUD 생성
 * 2023/04/13       오상현           후기 동주소, 닉네임 생성 
 * 
 * 
 */

public interface ReplyMapper {
	// 후기작성
	int insert(ReplyVO vo);
	
	// 후기 개별 확인
	ReplyVO read(Long rno);
	
	// bno 조회
	ReplyVO readBno(Long bno);
	List<ReplyVO> getListBno(Long bno);
	// 회원이 쓴 전체 후기 조회
	List<ReplyVO> getList(@Param("memberNo") Long memberNo);
	List<ReplyVO> getList2(@Param("bno") Long bno, @Param("rno") Long rno);
	// 후기 수정
	int update(ReplyVO vo);
	
	//후기 삭제
	int delete(Long rno);
	
	//글 삭제시 후기 삭제
	int deleteByBno(Long bno);
	
	//물품 번호를  통하여 구매자의 동주소 찾아오기
	List<ReplyVO> getAddress(Long bno);
	// 물품번호를 통하여 구매자의 닉네임 찾아오기
	List<ReplyVO> getNickName(Long bno);
	
//	// 물품번호를 통하여 평점 받아오기
//	List<ReplyVO> getRating(Long bno);

	int updateMemberFk(@Param("memberNo") Long memberNo);

	List<ReplyMypageDTO> getListMypage(@Param("memberNo") Long memberNo);
}
