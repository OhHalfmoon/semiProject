package kr.co.poetrypainting.service;

import java.util.List;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ReplyVO;
import kr.co.poetrypainting.domain.dto.ReplyMypageDTO;
import org.apache.ibatis.annotations.Param;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : ReplyService
 * author         :	오상현
 * date           : 2023/04/11
 * description    : ReplyService
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/11       오상현           후기 CRUD 서비스 생성
 * 2023/04/13       오상현           후기 동주소, 닉네임 생성
 * 
 * 
 */
public interface ReplyService {
	int register (ReplyVO vo);
	ReplyVO get (Long rno);
	ReplyVO getBno (Long bno);
	boolean remove (Long rno);
	int modify(ReplyVO vo);
	List<ReplyVO> getList (Long memberNo);
	List<ReplyVO> getListBno (Long bno);
	List<ReplyVO> getList2(Long bno, Long rno);
	List<ReplyVO> getAddress(Long bno);
	List<ReplyVO> getNickName(Long bno);

	List<ReplyMypageDTO> getListMypage(Long memberNo);

}
