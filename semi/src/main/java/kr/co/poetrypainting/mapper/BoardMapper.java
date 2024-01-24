package kr.co.poetrypainting.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.CategoryDTO;
import kr.co.poetrypainting.domain.dto.Criteria;

/**
 * 
* @packageName    : kr.co.poetrypainting.mapper
* @fileName        : BoardMapper.java
* @author        : 이시화
* @date            : 2023.04.09
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.04.09        이시화       최초 생성
 */
public interface BoardMapper {
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 통합 리스트 조회
	 * @return : BoardVo타입의 리스트 반환 
	 *
	 * @date : 2023. 4. 9.
	 */
	List<BoardVo> getList();
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 작성
	 * @param : BoardVo
	 * @return : void
	 *
	 * @date : 2023. 4. 9.
	 */
	Long insert(BoardVo vo);
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 삭제 
	 * @param : bno
	 *
	 * @date : 2023. 4. 9.
	 * 
	 */
	void delete(Long bno);
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 단일 조회 
	 * @param :bno
	 * @return : 조회할 게시글
	 *
	 * @date : 2023. 4. 9.
	 */
	BoardVo read(Long bno);
	
	/**
	 * 
	 * @author : 이시화
	 * @todo : 글 수정 
	 * @param : BoardVo
	 * @return : 수정된 글 번호
	 *
	 * @date : 2023. 4. 9.
	 */
	void update(BoardVo vo);
	

	/**
	 * 
	 * @author : 이시화
	 * @todo : 카테고리 리스트 가져오기 
	 * @return : 카테고리번호
	 *
	 * @date : 2023. 4. 11.
	 */
	List<CategoryDTO> getCategory();
	
	String getCategoryName(int cateNo);
	
	int getTotalCnt();
	int getTotalCntSearch(Criteria cri);
	int getAddrCnt(@Param("member") MemberVO member,@Param("option") Integer option);
	int getCateCnt(@Param("member")MemberVO member, @Param("cateNo")int cateNo,@Param("option") Integer option);
	List<Map<String, Object>> getListWithName(Criteria cri); 
	List<Map<String, Object>> getListMain(); 
	List<Map<String, Object>> getListWithAddr(@Param("criteria")Criteria cri,@Param("member") MemberVO member,@Param("option") Integer option); 
	Long readByMember(Long bno);
	List<Map<String, Object>> getListWithKeyword(Criteria cri);
	List<Map<String, Object>> getListCate(@Param("criteria")Criteria cri,@Param("member") MemberVO member, @Param("cateNo") int cateNo,@Param("option") Integer option);
	
	void uptime(BoardVo vo);
	List<Map<String, Object>> getMyList(@Param("memberNo") Long memberNo);
	
	void blind(Long bno);

	int updateMemberFK(@Param("memberNo") Long memberNo);
}
