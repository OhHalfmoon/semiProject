package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.*;

import java.util.List;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : MemberService
 * author         : 방한솔
 * date           : 2023/04/05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/05        방한솔            최초 생성
 * 2023/04/11        방한솔            가입시 기본 주소정보 저장기능 추가
 * 2023/04/12        방한솔            비밀번호 오류 수정
 * 2023/04/13        방한솔            이메일 인증 추가
 * 2023/04/21        방한솔            닉네임 인증 수정
 */
public interface MemberService {

    /**
     * 입력한 회원정보로 회원가입을 한다.
     *
     * @param memberVO 회원 VO
     * @param addressVO 주소 VO
     * @return 처리결과 (1 = 성공, 0 = 실패)
     */
    int register(MemberVO memberVO, AddressVO addressVO);


    /**
     * 입력받은 회원정보로 로그인 한다.
     *
     * @param loginDTO 회원 DTO
     * @return 회원 VO
     */
    MemberVO login(LoginDTO loginDTO);

    /**
     * 전체 회원정보를 조회한다.
     *
     * @return 회원VO 리스트
     */
    List<MemberVO> getList();

    /**
     * 회원정보를 수정한다.
     *
     * @param MemberModifyDto 회원 VO
     * @return 처리결과 (1 = 성공, 0 = 실패)
     */
    int modify(MemberModifyDto MemberModifyDto);

    int passwordChange(PassChangeDTO passChangeDTO);

    /**
     * 회원탈퇴를 한다.
     *
     * @param memberVO 회원 VO
     * @return 처리결과 (1 = 성공, 0 = 실패)
     */
    int withdraw(WithDrawDTO withDrawDTO);

    Boolean idCheck(String memberId);

    Boolean nickCheck(MemberVO memberVO);

    Boolean emailAuthCheck(String email, String code);

    MemberVO searchMember(Long memberNo);

    MypageDTO searchMemberMypage(Long memberNo);
}
