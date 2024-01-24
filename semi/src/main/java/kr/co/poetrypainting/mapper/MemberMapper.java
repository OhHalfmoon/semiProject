package kr.co.poetrypainting.mapper;

import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.MemberModifyDto;
import kr.co.poetrypainting.domain.dto.MypageDTO;
import kr.co.poetrypainting.domain.dto.PassChangeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * packageName    : kr.co.poetrypainting.mapper
 * fileName       : MemberMapper
 * author         : 방한솔
 * date           : 2023/04/05
 * description    : 회원 Mybatis Mapper Interface
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/05        방한솔            최초 생성
 */
public interface MemberMapper {

    /**
     * 입력받은 회원정보를 DB에 등록한다.
     *
     * @param memberVO 회원 VO
     * @return 등록한 회원번호
     */
    int insert(MemberVO memberVO);

    /**
     * 회원번호와 일치하는 회원을 조회한다.
     *
     * @param memberNo 회원번호
     * @return 회원 VO
     */
    MemberVO get(Long memberNo);

    /**
     * 회원아이디와 일치하는 회원을 조회한다.
     *
     * @param memberId 회원 ID
     * @return 회원 VO
     */
    MemberVO getByMemberId(String memberId);

    /**
     * 닉네임과 일치하는 회원정보를 조회한다.
     *
     * @param nickname 닉네임
     * @return 회원 VO
     */
    MemberVO getByNickname(String nickname);


    String idCheck(String memberId);

    /**
     * 전체 회원목록을 조회한다.
     *
     * @return the list
     */
    List<MemberVO> getList();

    /**
     * 입력받은 회원정보와 일치하는 회원을 수정한다.
     *
     * @param vo 회원 VO
     * @return 처리결과 (1 = 성공, 0 = 실패)
     */
    int update(MemberModifyDto vo);

    /**
     * 비밀번호를 변경한다.
     *
     * @param vo 회원 VO
     * @return 처리결과 (1 = 성공, 0 = 실패)
     */
    int changePassword(PassChangeDTO vo);

    /**
     * 회원번호와 일치하는 회원을 삭제한다.
     *
     * @param memberNo 회원번호
     * @return 처리결과 (1 = 성공, 0 = 실패)
     */
    int delete(Long memberNo);

    /**
     * 마이페이지의 회원정보를 조회한다.
     *
     * @param memberNo 로그인한 회원번호
     * @return the mypage
     */
    MypageDTO getMypage(Long memberNo);

    int increaseCredit(@Param("memberNo") Long memberNo, @Param("amount") int amount);
    int decreaseCredit(@Param("memberNo")Long memberNo, @Param("amount")int amount);
    int setCredit(@Param("memberNo")Long memberNo, @Param("credit")int credit);
}
