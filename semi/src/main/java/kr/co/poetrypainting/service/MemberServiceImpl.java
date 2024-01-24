package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.*;
import kr.co.poetrypainting.domain.dto.*;
import kr.co.poetrypainting.mapper.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : MemberServiceImpl
 * author         : 방한솔
 * date           : 2023/04/06
 * description    : 회원 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/06        방한솔             최초 생성
 * 2023/04/11        방한솔             가입시 기본 주소정보 저장기능 추가
 * 2023/04/12        방한솔             비밀번호 오류 수정
 * 2023/04/13        방한솔             이메일 인증 추가
 */
@Service
@AllArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    private final AddressMapper addressMapper;
    private final BoardMapper boardMapper;
    private final CheatMapper cheatMapper;
    private final ReplyMapper replyMapper;
    private final TradeMapper tradeMapper;
    private final WithdrawalMapper withdrawalMapper;

    private final EmailAuthMapper emailAuthMapper;

    private final ShopMapper shopMapper;

    @Override
    @Transactional
    public int register(MemberVO memberVO, AddressVO addressVO) {
        // 아이디 중복 확인
        // 닉네임 중복 확인
        // 패스워드 일치여부 확인

        memberVO.setPassword(BCrypt.hashpw(memberVO.getPassword(), BCrypt.gensalt()));

        long result = (long) memberMapper.insert(memberVO);

        addressVO.setMemberNo(memberVO.getMemberNo());

        addressMapper.insertSelectkey(addressVO);

        shopMapper.insertShop(memberVO.getMemberNo(), memberVO.getNickname());

        return (int)result;
    }

    @Override
    public MemberVO login(LoginDTO loginDto) {


        MemberVO inputMember = MemberVO.builder()
                .memberId(loginDto.getMemberId())
                .password(loginDto.getPassword())
                .build();

        String memberId = inputMember.getMemberId();


        MemberVO searchMember = memberMapper.getByMemberId(memberId);

        MemberVO result = null;

        if(searchMember == null) {
            return null;
        }
        // log.info("memberVO.getPassword() : {}", inputMember.getPassword());
        // log.info("searchMember.getPassword() : {}", searchMember.getPassword());


        boolean checkpw = BCrypt.checkpw(inputMember.getPassword(), searchMember.getPassword());

        log.info("checkpw : {}", checkpw);

        if (checkpw) {
            result = searchMember;
        }

        return result;
    }

    @Override
    public List<MemberVO> getList() {
        return memberMapper.getList();
    }

    @Override
    public int modify(MemberModifyDto MemberModifyDto) {
        // 닉네임 중복확인

        return memberMapper.update(MemberModifyDto);
    }

    @Override
    public int passwordChange(PassChangeDTO passChangeDTO) {
        MemberVO memberVO = memberMapper.get(passChangeDTO.getMemberNo());

        log.warn("passChangeDTO : {}", passChangeDTO);
        log.warn("memberVO : {}", memberVO);

        // 이전 비밀번호 확인
        boolean checkOldPw = BCrypt.checkpw(passChangeDTO.getPassword(), memberVO.getPassword());

        if(!checkOldPw){
            return -1;
        }

        // 새 비밀번호 양식 validation 확인
        // 비밀번호, 비밀번호 확인 일치여부 확인
        log.warn("passChangeDTO : {}", passChangeDTO);

        String pw = passChangeDTO.getNewPassword();

        String hashpw = BCrypt.hashpw(passChangeDTO.getNewPassword(), BCrypt.gensalt());

        Boolean isSalted = BCrypt.checkpw(pw, hashpw);

        log.warn("pw : {}  hashpw : {}", pw, hashpw);

        log.warn("isSalted : {}", isSalted);

        //return 0;

        passChangeDTO.setNewPassword(hashpw);
        return memberMapper.changePassword(passChangeDTO);
    }

    @Override
    @Transactional
    public int withdraw(WithDrawDTO withDrawDTO) {
        // 탈퇴사유 추가
        WithdrawalVO withdrawalVO = new WithdrawalVO();
        withdrawalVO.setMemberId(withDrawDTO.getMemberId());
        withdrawalVO.setReason(withDrawDTO.getReason());

        // 탈퇴사유 추가
        withdrawalMapper.insert(withdrawalVO);

        // 주소 제거

        // 게시글 회원 fk 업데이트(NULL)
        boardMapper.updateMemberFK(withDrawDTO.getMemberNo());

        addressMapper.updateMemberNo(withDrawDTO.getMemberNo());

        // 신고 회원 fk 업데이트(NULL)
        cheatMapper.updateMemberFK(withDrawDTO.getMemberNo());

        // 댓글 회원 fk 업데이트(NULL)
        replyMapper.updateMemberFk(withDrawDTO.getMemberNo());

        // 거래 회원 fk 업데이트(NULL)
        tradeMapper.updateMemberFk(withDrawDTO.getMemberNo());

        int result = memberMapper.delete(withDrawDTO.getMemberNo());

        return result;
    }


    @Override
    public Boolean idCheck(String memberId){
        // MemberVO member = memberMapper.getByMemberId(memberId);

        String member = memberMapper.idCheck(memberId);

        // member가 있다 -> 값이 존재함 -> false
        // member가 없다 -> 값이 존재하지않음 -> true
        return member == null;
    }

    @Override
    public Boolean nickCheck(MemberVO memberVO) {
        //MemberVO member = memberMapper.getByNickname(nickname);
        MemberVO member = memberMapper.getByNickname(memberVO.getNickname());

        // 입력한 닉네임과 일치하는 회원이 존재? true : 중복 있음, false : 중복 없음
        // 검색한 닉네임이 MemberVO와 일치?

        Boolean result = null;

        if(member != null && memberVO.getMemberNo() != null){

            result = member == null || member.getMemberNo() == memberVO.getMemberNo();
        } else {
            result =  member == null;
        }

        return result;
    }

    @Override
    public Boolean emailAuthCheck(String email, String code) {
        EmailAuthVO recentOne = emailAuthMapper.getRecentOne(email);

        return recentOne != null && recentOne.getCode().equals(code);
    }

    @Override
    public MemberVO searchMember(Long memberNo) {
        return memberMapper.get(memberNo);
    }

    @Override
    public MypageDTO searchMemberMypage(Long memberNo) {
        return memberMapper.getMypage(memberNo);
    }
}
