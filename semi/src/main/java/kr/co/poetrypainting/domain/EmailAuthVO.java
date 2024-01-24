package kr.co.poetrypainting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * packageName    : kr.co.poetrypainting.domain
 * fileName       : EmailAuthVO
 * author         : banghansol
 * date           : 2023/04/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        banghansol       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("emailAuth")
public class EmailAuthVO {
    /**
     * 인증번호
     */
    private Long authNo;

    /**
     * 이메일
     */
    private String email;

    /**
     * 인증코드
     */
    private String code;

    /**
     * 등록날짜
     */
    private Date regdate;
}
