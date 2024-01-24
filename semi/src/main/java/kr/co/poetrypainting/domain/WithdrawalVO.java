package kr.co.poetrypainting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * packageName    : kr.co.poetrypainting.domain
 * fileName       : WithdrawalVO
 * author         : 방한솔
 * date           : 2023/04/06
 * description    : 탈퇴회원 VO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/06        방한솔            최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Alias("withdrawal.jsp")
public class WithdrawalVO {
    /**
     * 회원 ID
     */
    private String memberId;

    /**
     * 탈퇴 사유
     */
    private String reason;
}
