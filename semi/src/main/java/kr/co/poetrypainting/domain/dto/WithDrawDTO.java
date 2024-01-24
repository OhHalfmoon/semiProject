package kr.co.poetrypainting.domain.dto;

import kr.co.poetrypainting.domain.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : WithDrawDTO
 * author         : banghansol
 * date           : 2023/04/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/24        banghansol       최초 생성
 */
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithDrawDTO extends MemberVO {
    private String reason;

    public WithDrawDTO(MemberVO memberVO, String reason) {
        super(memberVO.getMemberNo(),
                memberVO.getMemberId(),
                memberVO.getPassword(),
                memberVO.getEmail(),
                memberVO.getNickname(),
                memberVO.getRegDate(),
                memberVO.getUpdateDate(),
                memberVO.getCredit(),
                memberVO.getAdmin());
        this.reason = reason;
    }
}
