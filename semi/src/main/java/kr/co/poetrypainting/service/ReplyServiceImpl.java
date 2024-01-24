package kr.co.poetrypainting.service;

import java.util.List;

import kr.co.poetrypainting.domain.dto.ReplyMypageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ReplyVO;
import kr.co.poetrypainting.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : ReplyServiceImpl
 * author         :	오상현
 * date           : 2023/04/11
 * description    : ReplyServiceImpl
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/11       오상현           후기 CRUD 서비스 생성
 * 2023/04/13       오상현           후기 동주소, 닉네임 생성 
 * 
 */
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public int register(ReplyVO vo) {		
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return replyMapper.read(rno);
	}
	
	@Override
	public ReplyVO getBno(Long bno) {

		return replyMapper.readBno(bno);
	}

	@Override
	@Transactional
	public boolean remove(Long rno) {
	int result = replyMapper.delete(rno);
	return result > 0;
	}

	@Override
	public int modify(ReplyVO vo) {
		return replyMapper.update(vo);
	}

	@Override
	public List<ReplyVO> getList(Long memberNo) {
		return replyMapper.getList(memberNo);
	}
	
	@Override
	public List<ReplyVO> getList2(Long bno, Long rno) {
		return replyMapper.getList2(bno, rno);
	}

	@Override
	public List<ReplyVO> getListBno(Long bno) {

		return replyMapper.getListBno(bno);
	}

	@Override
	public List<ReplyVO> getAddress(Long bno) {
		return replyMapper.getAddress(bno);
	}

	@Override
	public List<ReplyVO> getNickName(Long bno) {		
		return replyMapper.getNickName(bno);
	}

	@Override
	public List<ReplyMypageDTO> getListMypage(Long memberNo) {
		return replyMapper.getListMypage(memberNo);
	}


}
