package kr.co.poetrypainting.domain.dto;

import kr.co.poetrypainting.domain.MemberVO;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : MemberModifyDto
 * author         : banghansol
 * date           : 2023/04/20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/20        banghansol       최초 생성
 */
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("memberModify")
public class MemberModifyDto extends MemberVO {
    /**
     * 변경할 이메일
     */
    private String newEmail;

    /**
     * 이메일 인증코드
     */
    private String code;

    /**
     * MemberVO를 받는 생성자
     *
     * @param memberVO the member vo
     */
    public MemberModifyDto(MemberVO memberVO) {
        super(memberVO.getMemberNo(),
                memberVO.getMemberId(),
                memberVO.getPassword(),
                memberVO.getEmail(),
                memberVO.getNickname(),
                memberVO.getRegDate(),
                memberVO.getUpdateDate(),
                memberVO.getCredit(), memberVO.getAdmin());
        this.newEmail = newEmail;
        this.code = code;
    }
}
