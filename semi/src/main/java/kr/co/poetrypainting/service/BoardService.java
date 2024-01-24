package kr.co.poetrypainting.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.AttachDTO;
import kr.co.poetrypainting.domain.dto.CategoryDTO;
import kr.co.poetrypainting.domain.dto.Criteria;

/**
 * 
* @packageName    : kr.co.poetrypainting.service
* @fileName        : BoardService.java
* @author        : 이시화
* @date            : 2023.04.09
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.04.09        이시화       최초 생성
 */
public interface BoardService {
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 등록 
	 * @param : BoardVo
	 * @return : void
	 *
	 * @date : 2023. 4. 9.
	 */
	void register(BoardVo vo);
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 단일 조회
	 * @param : bno
	 * @return : BoardVo
	 *
	 * @date : 2023. 4. 10.
	 */
	BoardVo get(Long bno);
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 수정 
	 * @param : BoardVo
	 * @return : Long (수정된 글 번호)
	 *
	 * @date : 2023. 4. 10.
	 */
	void modify(BoardVo vo);
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 삭제 
	 * @param : bno
	 * @return : void
	 *
	 * @date : 2023. 4. 10.
	 */
	void remove(Long bno);
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 목록 조회 
	 * @param :
	 * @return : List<BoardVo> 
	 *
	 * @date : 2023. 4. 10.
	 */
	List<BoardVo> getList();
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 카테고리 가져오기 
	 * @return : 카테고리 리스트
	 *
	 * @date : 2023. 4. 11.
	 */
	
	List<CategoryDTO> getCategoryList();
	
	String getCategoryName(int cateNo);
	
	List<Map<String, Object>> getListWhitAll(Criteria cri); 
	
	int getTotalCnt();
	
	int getTotalCntSearch(Criteria cri);
	
	int getAddrCnt(MemberVO member,Integer option);
	int getCateCnt(MemberVO member,int cateNo,Integer option);

	List<Map<String, Object>> getListWithName(Criteria cri); 
	List<Map<String, Object>> listMain(); 
	List<Map<String, Object>> getListWithAddr(Criteria cri,MemberVO member, Integer option); 
	
	List<Map<String, Object>> getListWithKeyword(Criteria cri);
	List<Map<String, Object>> getListCategory(Criteria cri,MemberVO member,int cateNo,Integer option);

	String deleteFile(AttachDTO dto); 
	Long readByMember(Long bno);
	void uptime(BoardVo vo);
}
