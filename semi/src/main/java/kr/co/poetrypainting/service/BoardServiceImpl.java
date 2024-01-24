package kr.co.poetrypainting.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.poetrypainting.controller.BoardController;
import kr.co.poetrypainting.controller.FileController;
import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.AttachDTO;
import kr.co.poetrypainting.domain.dto.CategoryDTO;
import kr.co.poetrypainting.domain.dto.Criteria;
import kr.co.poetrypainting.mapper.AttachMapper;
import kr.co.poetrypainting.mapper.BoardMapper;
import kr.co.poetrypainting.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
/**
 * 
* @packageName    : kr.co.poetrypainting.service
* @fileName        : BoardServiceImpl.java
* @author        : 이시화
* @date            : 2023.04.09
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.04.09        이시화       최초 생성
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public void register(BoardVo vo) {
		boardMapper.insert(vo);
		Long bno = vo.getBno(); 
		log.warn(bno);
		log.warn(vo);
		int idx = 0; 
		List<AttachDTO> list = vo.getAttachs();
		if(list == null || list.size() == 0){
			return;
		}
		log.warn(list);
		for (AttachDTO attach : list) {
			attach.setBno(bno);
			attach.setUuidIdx(idx++);
			attachMapper.insert(attach);
			log.warn(attach);
		}
	}

	@Override
	public BoardVo get(Long bno) {
		return boardMapper.read(bno);
	}


	@Override
	@Transactional
	public void remove(Long bno) {
		
		attachMapper.deleteAll(bno);
		boardMapper.delete(bno);
	}

	@Override
	public List<BoardVo> getList() {
		
		
		return boardMapper.getList();
	}
	
	@Override
	public List<CategoryDTO> getCategoryList() {
		
		return boardMapper.getCategory();
	}
	
	public List<Map<String, Object>> getListWhitAll(Criteria cri){
		return boardMapper.getListWithName(cri);
	}

	@Override
	public int getTotalCnt() {
		
		return boardMapper.getTotalCnt();
	}

	@Override
	public int getTotalCntSearch(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCntSearch(cri);
	}

	@Override
	public int getAddrCnt(MemberVO member,Integer option) {
		// TODO Auto-generated method stub
		return boardMapper.getAddrCnt(member,option);
	}

	@Override
	public List<Map<String, Object>> getListWithName(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getListWithName(cri);
	}

	@Override
	public List<Map<String, Object>> getListWithAddr(Criteria cri, MemberVO member , Integer option) {
		// TODO Auto-generated method stub
		return boardMapper.getListWithAddr(cri, member, option);
	}

	@Override
	public List<Map<String, Object>> getListWithKeyword(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getListWithKeyword(cri);
	}; 
	
	@Override
	public List<Map<String, Object>> getListCategory(Criteria cri, MemberVO member, int cateNo,Integer option) {
		// TODO Auto-generated method stub
		return boardMapper.getListCate(cri, member, cateNo,option);
	}
	
	@Override
	public int getCateCnt(MemberVO member, int cateNo,Integer option) {
		// TODO Auto-generated method stub
		return boardMapper.getCateCnt(member, cateNo,option);
	}
	@Override
	public String deleteFile(AttachDTO dto) {
		
	log.info(dto);
		
		String s = FileController.getPATH() + "/" + dto.getAttPath() + "/" + dto.getAttUuid() + "_" + dto.getAttName();
		
		File file = new File(s);
		log.info(file);
		file.delete();
		
		s = FileController.getPATH() + "/" + dto.getAttPath() + "/s1_" + dto.getAttUuid() + "_" + dto.getAttName();
		File file2 = new File(s);
		log.info(file2);
		file2.delete();
					
		s = FileController.getPATH() + "/" + dto.getAttPath() + "/s2_" + dto.getAttUuid() + "_" + dto.getAttName();
		File file3 = new File(s);
		log.info(file3);
		file3.delete();

		return dto.getAttUuid();
	}
	
	@Override
	public void modify(BoardVo vo) {
		attachMapper.deleteAll(vo.getBno());
		
		//수정 될 리스트
		List<AttachDTO> list = vo.getAttachs();
		int idx = 0;
		for (AttachDTO attach : list) {
			attach.setBno(vo.getBno());
			attach.setUuidIdx(idx++);
			attachMapper.insert(attach);
		}
		boardMapper.update(vo);
	}

	@Override
	public Long readByMember(Long bno) {
			boardMapper.readByMember(bno);
		return null;
	}

	@Override
	public String getCategoryName(int cateNo) {
		
		return boardMapper.getCategoryName(cateNo);
	}
	
	public void uptime(BoardVo vo){
		boardMapper.uptime(vo);
	}

	@Override
	public List<Map<String, Object>> listMain() {
		// TODO Auto-generated method stub
		return boardMapper.getListMain();
	}
	
	
	
}
